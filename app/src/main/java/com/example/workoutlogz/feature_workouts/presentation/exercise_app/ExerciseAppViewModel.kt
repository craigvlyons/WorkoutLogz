package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.ADD_EXERCISE_NAME_SCREEN
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases


@HiltViewModel
class ExerciseAppViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val exerciseListUseCases: ExerciseListUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(ExerciseAppState())
    val state: StateFlow<ExerciseAppState> = _state

    init {
        getExercises()
        getExerciseLists()
    }

    /* TODO
        create the use cases.
        get last exercise summery - use the last date
        list of all the exercise names
        display each exercise list


     */
    fun onEvent(event: ExerciseEvent){
        when(event){
        is ExerciseEvent.NavigateSettings -> {
            onSettingsClick { event.screenName }
        }
            is ExerciseEvent.DeleteById -> {
                Log.d(TAG, event.exerciseId.toString())
                deleteExerciseById(event.exerciseId)
            }
            is ExerciseEvent.ExerciseListId -> {
                // onExerciseListClick(event.exerciseId)
                // openScreen("$EXERCISELIST_ID?$EXERCISELIST_ID={${event.exerciseId}}")
            }
        }
    }

    private fun deleteExerciseById(exerciseId: Int) {
        viewModelScope.launch {
            try {
                if (exerciseId != null){
                    exerciseUseCases.deleteExerciseByIdUseCase(exerciseId)
                    getExercises()
                }
            }catch (ex: Exception){
                Log.e(TAG, ex.toString())
            }
        }
    }

    private fun getExercises() {
        viewModelScope.launch {
            exerciseUseCases.getAllExerciseUseCase.invoke().collect { exercises ->
                _state.value = _state.value.copy(
                exercises = exercises
                )
            }
        }
    }

    private fun getExerciseLists() {
        viewModelScope.launch {
            exerciseListUseCases.getAllExerciseListUseCase.invoke().collect { exerciseList ->
                _state.value = _state.value.copy(
                    exerciseList = exerciseList
                )
            }
        }
    }

    // Navigation functions...
    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onExerciseClick(openScreen: (String) -> Unit) = openScreen(ADD_EXERCISE_NAME_SCREEN)

    fun onExerciseListClick(openScreen: (String) -> Unit, exerciseListId: Int) {
         openScreen("$EXERCISE_LIST_SCREEN/$exerciseListId")
    }

    companion object {
        private const val TAG = "ExerciseAppViewModel"
    }
}