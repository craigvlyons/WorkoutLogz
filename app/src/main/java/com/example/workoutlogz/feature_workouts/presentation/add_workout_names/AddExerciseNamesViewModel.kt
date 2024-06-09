package com.example.workoutlogz.feature_workouts.presentation.add_workout_names

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.ExerciseUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import androidx.compose.ui.text.capitalize
import com.example.workoutlogz.BuildConfig


@HiltViewModel
class AddExerciseNamesViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(AddExerciseNamesState())
    val state: StateFlow<AddExerciseNamesState> = _state

    private val _nameTextField = mutableStateOf(
        AddExerciseTextFieldState(
            hint = "Workout Name..."
        )
    )
    var nameTextField: State<AddExerciseTextFieldState> = _nameTextField
    // Selected exercises names set
    private var _exercisesNames = mutableStateOf<Set<String>>(setOf())
    val exercisesNames: State<Set<String>> = _exercisesNames

    init {
        getExerciseNames()
    }

    fun onEvent(event: AddNewExerciseName) {
        when (event) {
            is AddNewExerciseName.EnteredSearch -> {
                _nameTextField.value = _nameTextField.value.copy(
                    text = event.value
                )
            }
            is AddNewExerciseName.ChangeSearchFocus -> {
                _nameTextField.value = _nameTextField.value.copy(
                    isHintVisible = !event.focusState.isFocused && _nameTextField.value.text.isBlank()
                )
            }
            is AddNewExerciseName.SaveExercise -> {
                // save exercise to database...
            }
            is AddNewExerciseName.AddExercise -> {
                saveExercise()
                // reset text box
                _nameTextField.value = _nameTextField.value.copy(
                    text = ""
                )
            }
            is AddNewExerciseName.DeleteExerciseById -> {
                deleteExercisesById(event.exerciseId)
            }
            else -> {}
        }
    }

    private fun deleteExercisesById(exerciseId: Int) {
        viewModelScope.launch {
            try {
                exerciseUseCases.deleteExerciseByIdUseCase(exerciseId)
                Log.d(TAG, "Deleted Exercise Id: $exerciseId")
            } catch (ex: Exception) {
                // this is empty
            }
            //TODO: need to remove the exercises that the exercise lists are using because the exercise does not exist.
        }
    }

    private fun saveExercise() {
        // Retrieve the text from the input field, trim spaces, and convert to title case
        val exerciseName = _nameTextField.value.text.trim()
            .split(" ")  // Split the string into words based on spaces
            .joinToString(" "){it.capitalize()} // Capitalize the first letter of each word and join them back into a string
        if (exerciseName.isBlank() || _exercisesNames.value.contains(exerciseName)) {
            // Handle empty input, e.g., update state to show an error message
            // exercise name already exists
            Log.i(TAG, "Exercise Name is blank or Exercise Name already exists.")
            return
        }

        viewModelScope.launch {
            try {
                val newExercise = Exercise(name = exerciseName)
                exerciseUseCases.addNewExerciseUseCase.invoke(newExercise)
                getExerciseNames()
                // Optionally, update state to show success message
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "Debug message")
                }
            } catch (ex: Exception) {
                // Log error and update state to reflect failure
            }
        }
    }

    private fun getExerciseNames() {
        viewModelScope.launch {
            exerciseUseCases.getAllExerciseUseCase().collect { exerciseList ->
                _state.value = _state.value.copy(
                    exerciseList = exerciseList
                )
                _exercisesNames.value = exerciseList.map { it.name }.toSet()
            }
        }
    }


    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit) =
        openAndPopUp(EXERCISE_APP_SCREEN, SETTINGS_SCREEN)

    fun restartApp(restartApp: (String) -> Unit) = restartApp(SPLASH_SCREEN)

    /*
        Verbose: Log.v(tag, message)
        Debug: Log.d(tag, message)
        Info: Log.i(tag, message)
        Warning: Log.w(tag, message)
        Error: Log.e(tag, message)
     */
    companion object {
        private const val TAG = "AddExerciseNames"
    }
}