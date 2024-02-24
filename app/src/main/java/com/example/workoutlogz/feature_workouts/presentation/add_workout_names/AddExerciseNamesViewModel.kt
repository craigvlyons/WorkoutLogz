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
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.ExerciseUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
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
                deleteExerciesById(event.exerciseId)
            }
            else -> {}
        }
    }

    private fun deleteExerciesById(exerciseId: Int) {
        viewModelScope.launch {
            try {
                exerciseUseCases.deleteExerciseByIdUseCase(exerciseId)
                Log.d(TAG, exerciseId.toString())
            } catch (ex: Exception) {
                // this is empty
            }
        }
    }

    private fun saveExercise() {
        val exerciseName = _nameTextField.value.text.trim()
        if (exerciseName.isBlank()) {
            // Handle empty input, e.g., update state to show an error message
            return
        }
        viewModelScope.launch {
            try {
                val newExercise = Exercise(name = exerciseName)
                //TODO add check that workout exists before saving
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