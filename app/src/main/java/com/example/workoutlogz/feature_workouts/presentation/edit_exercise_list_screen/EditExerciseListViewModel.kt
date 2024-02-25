package com.example.workoutlogz.feature_workouts.presentation.edit_exercise_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.EDIT_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.ExerciseUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListState
import com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen.BasicTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val exerciseListUseCases: ExerciseListUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _listNameTextField = mutableStateOf(
        BasicTextFieldState(
            hint = "Full Body, Legs, Tuesday..."
        )
    )
    private val _descriptionTextField = mutableStateOf(
        BasicTextFieldState(
            hint = "Exercise List Description..."
        )
    )

    val exerciseListName: State<BasicTextFieldState> = _listNameTextField
    val description: State<BasicTextFieldState> = _descriptionTextField
    private val _state = MutableStateFlow(ExerciseListState())
    val state: StateFlow<ExerciseListState> = _state
    private var _title = mutableStateOf("Exercise List")
    var title: State<String> = _title
    private val _allExerciseNames = mutableStateOf<List<String>>(emptyList())
    val allExerciseNames: State<List<String>> = _allExerciseNames

    // Selected exercises state
    private val _selectedExercises = mutableStateOf<Set<String>>(setOf())
    val selectedExercises: State<Set<String>> = _selectedExercises

    val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1
    // init to load fields...
    init {
        getAllExerciseNamesList()

        Log.i(TAG, "exercise id: $exerciseListId")

        if (exerciseListId != -1) {
            viewModelScope.launch {
                try {
                    if (exerciseListId != -1) {
                        loadTextBoxes(exerciseListId)
                        Log.i(
                            TAG,
                            "Exercise List name: ${_state.value.exerciseListWithWorkouts?.exerciseList?.name}"
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error fetching ExerciseListWithWorkouts", e)
                    // Handle error, maybe update the state with an error message
                }
                // if state is successful load name and description into text boxes.

            }
        }
    }

    fun onEvent(event: EditExerciseListEvent) {
        when (event) {
            is EditExerciseListEvent.EnteredTitle -> {
                _listNameTextField.value = _listNameTextField.value.copy(
                    text = event.value
                )
            }

            is EditExerciseListEvent.EnteredDescription -> {
                _descriptionTextField.value = _descriptionTextField.value.copy(
                    text = event.value
                )
            }

            is EditExerciseListEvent.ChangeTitleFocus -> {
                _listNameTextField.value = _listNameTextField.value.copy(
                    isHintVisible = !event.focusState.isFocused && _listNameTextField.value.text.isBlank()
                )
            }

            is EditExerciseListEvent.ChangeDescriptionFocus -> {
                _descriptionTextField.value = _descriptionTextField.value.copy(
                    isHintVisible = !event.focusState.isFocused && _descriptionTextField.value.text.isBlank()
                )
            }

            is EditExerciseListEvent.UpdateExerciseList -> {
                // Update ExerciseList...
                //addExerciseList()
            }

            is EditExerciseListEvent.DeleteExerciseList -> TODO()
            is EditExerciseListEvent.LoadExerciseList -> TODO()
            is EditExerciseListEvent.ToggleExerciseSelection -> toggleExerciseSelection(event.exerciseName)
            is EditExerciseListEvent.SaveExerciseList -> SaveExerciseList()
        }
    }

    private fun SaveExerciseList() {
        if (_listNameTextField.value.text.isBlank() || _descriptionTextField.value.text.isBlank()) {
            Log.i(TAG, "text is blank")
            return
        }
        _state.value.exerciseList = _state.value.exerciseList?.copy(
            name = _listNameTextField.value.text,
            description = _descriptionTextField.value.text
        )
        val exerciseList = _state.value.exerciseList
        viewModelScope.launch {
            try {
                if (exerciseList != null) {
                    Log.i(TAG, "updating exercise ${exerciseList.name}")
                    exerciseListUseCases.updateExerciseListUseCase(exerciseList)
                }
            } catch (ex: Exception) {
                Log.i(TAG, "Failed to save exercise List")
                Log.e(TAG, ex.toString())
            }
            Log.i(TAG, "saved")
        }

    }

    private fun loadTextBoxes(exerciseListId: Int){
        viewModelScope.launch {
            try {
                // Assuming getExerciseListByIdUseCase returns a single ExerciseList instance.
                val exerciseList = exerciseListUseCases.getExerciseListByIdUseCase(exerciseListId)
                    .first() // Use first() if it's a Flow and you just need the latest value.
                // Now you can directly access exerciseList as it's not a Flow here.
                _state.value = _state.value.copy(
                    exerciseList = exerciseList, // If you need to store it as a Flow in the state.
                    exerciseNamesList = exerciseList.exerciseNames // Assuming exerciseNames is the correct field name.
                )
                _title.value = exerciseList.name
                _selectedExercises.value = exerciseList.exerciseNames.toSet()
                _listNameTextField.value = _listNameTextField.value.copy(
                    text =  exerciseList.name,
                    isHintVisible = false
                )
                _descriptionTextField.value = _descriptionTextField.value.copy(
                    text = exerciseList.description,
                    isHintVisible = false
                )
            } catch (e: Exception) {
                // Handle any errors, for example updating the state with error information
                Log.e(TAG, "Error fetching ExerciseList", e)
            }
        }
    }

    private fun loadExerciseList(exerciseListId: Int) {
        viewModelScope.launch {
            try {
                // Assuming getExerciseListByIdUseCase returns a single ExerciseList instance.
                val exerciseList = exerciseListUseCases.getExerciseListByIdUseCase(exerciseListId)
                    .first() // Use first() if it's a Flow and you just need the latest value.
                // Now you can directly access exerciseList as it's not a Flow here.
                _state.value = _state.value.copy(
                    exerciseList = exerciseList, // If you need to store it as a Flow in the state.
                    exerciseNamesList = exerciseList.exerciseNames // Assuming exerciseNames is the correct field name.
                )
                _title.value = exerciseList.name
                _selectedExercises.value = exerciseList.exerciseNames.toSet()
            } catch (e: Exception) {
                // Handle any errors, for example updating the state with error information
                Log.e(TAG, "Error fetching ExerciseList", e)
            }
        }
    }

    private fun getAllExerciseNamesList() {
        viewModelScope.launch {
            exerciseUseCases.getAllExerciseUseCase().collect { exercises ->
                _allExerciseNames.value = exercises.map { it.name }
            }
        }
    }
    private fun toggleExerciseSelection(exerciseName: String) {
        val currentSet = _selectedExercises.value
        if (currentSet.contains(exerciseName)) {
            _selectedExercises.value = currentSet - exerciseName // Deselect

        } else {
            _selectedExercises.value = currentSet + exerciseName // Select
        }
        // Proceed only if exerciseList is not null
        _state.value.exerciseList?.let { exerciseList ->
            if (currentSet.contains(exerciseName)) {
                // Remove exercise name
                val updatedExerciseNames = exerciseList.exerciseNames - exerciseName
                updateExerciseListNamesDb(exerciseList.id, updatedExerciseNames)
            } else {
                // Add exercise name
                val updatedExerciseNames = exerciseList.exerciseNames + exerciseName
                updateExerciseListNamesDb(exerciseList.id, updatedExerciseNames)
            }
        }
    }
    private fun updateExerciseListNamesDb(exerciseListId: Int, exerciseNames: List<String>) {
        viewModelScope.launch {
            try {
                exerciseListUseCases.updateExerciseListNamesUseCase(exerciseListId, exerciseNames)
                state.value.exerciseList?.id?.let {
                    loadExerciseList(it)
                }
                Log.i(TAG, "Updating ExerciseList")
            } catch (e: Exception) {
                // Handle any errors, for example updating the state with error information
                Log.e(TAG, "Error Updating ExerciseList", e)
            }
        }
    }

    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit) =
        openAndPopUp(
            "$EXERCISE_LIST_SCREEN/$exerciseListId"
            , EDIT_EXERCISE_LIST_SCREEN)

    fun restartApp(restartApp: (String) -> Unit) = restartApp(SPLASH_SCREEN)

    /*
        Verbose: Log.v(tag, message)
        Debug: Log.d(tag, message)
        Info: Log.i(tag, message)
        Warning: Log.w(tag, message)
        Error: Log.e(tag, message)
     */
    companion object {
        private const val TAG = "Edit Exercise List"
    }
}