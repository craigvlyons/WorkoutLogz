package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.workoutlogz.feature_workouts.ADD_EXERCISE_NAME_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.NEW_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.ExerciseUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetExerciseListWithWorkouts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val exerciseListUseCases: ExerciseListUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(ExerciseListState())
    val state: StateFlow<ExerciseListState> = _state

    private var _title = mutableStateOf("Exercise List")
    var title: State<String> = _title

    private val _allExerciseNames = mutableStateOf<List<String>>(emptyList())
    val allExerciseNames: State<List<String>> = _allExerciseNames

    // Selected exercises state
    private val _selectedExercises = mutableStateOf<Set<String>>(setOf())
    val selectedExercises: State<Set<String>> = _selectedExercises


    init {
        val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1

        getAllExerciseNamesList()

        Log.i(TAG, "exercise id: $exerciseListId")

         if (exerciseListId != -1)  {
            viewModelScope.launch {
                try {
                    if (exerciseListId != -1) {
                        loadExerciseList(exerciseListId)
                        Log.i(TAG, "Exercise List name: ${_state.value.exerciseListWithWorkouts?.exerciseList?.name}")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error fetching ExerciseListWithWorkouts", e)
                    // Handle error, maybe update the state with an error message
                }
                //Log.i(TAG, "Exercise List name: ${_state.value.exerciseList.}")
            }
        }
}

    // Handle events
    fun onEvent(event: ExerciseListEvent) {
        when (event) {
            is ExerciseListEvent.ToggleExerciseSelection -> toggleExerciseSelection(event.exerciseName)
            is ExerciseListEvent.LoadExerciseList -> state.value.exerciseList?.id?.let {
                loadExerciseList(it)
            }

            else -> {}
        }
    }

    private fun loadExerciseList(exerciseListId: Int) {
        viewModelScope.launch {
            try {
                // Assuming getExerciseListByIdUseCase returns a single ExerciseList instance.
                val exerciseList = exerciseListUseCases.getExerciseListByIdUseCase(exerciseListId).first() // Use first() if it's a Flow and you just need the latest value.
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



    private fun getAllExerciseNamesList(){
        viewModelScope.launch {
             exerciseUseCases.getAllExerciseUseCase().collect { exercises ->
                _allExerciseNames.value = exercises.map { it.name }
            }
        }
    }

    // Function to handle selection/deselection of exercises
    fun toggleExerciseSelection(exerciseName: String) {
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


    // Navigation functions...
    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onExerciseClick(openScreen: (String) -> Unit) = openScreen(ADD_EXERCISE_NAME_SCREEN)
    fun navBack(openAndPopUp: (String, String) -> Unit ) = openAndPopUp(EXERCISE_APP_SCREEN, EXERCISE_LIST_SCREEN)


    companion object {
        private const val TAG = "ExerciseListViewModel"
    }

}