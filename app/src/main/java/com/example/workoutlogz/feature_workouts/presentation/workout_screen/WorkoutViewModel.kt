package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EDIT_WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.WORKOUT_NAME
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.WorkoutUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(WorkoutState())
    val state: StateFlow<WorkoutState> = _state
    val exerciseListId: Int = savedStateHandle[EXERCISELIST_ID] ?: -1
    val workoutName: String = savedStateHandle[WORKOUT_NAME] ?: ""
    var _lastWorkout =
        MutableStateFlow(Workout(name = workoutName, set = 0, rep = 0, date = LocalDateTime.now(), note = ""))
    var lastWorkout: StateFlow<Workout> = _lastWorkout

    var _workoutId: MutableState<Int> = mutableStateOf(-1)
    var workoutId: State<Int> = _workoutId

    init {
        loadWorkoutList(workoutName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getLastWorkout() {
        val lastWorkout = _state.value.workouts.maxByOrNull { it.date }
        lastWorkout.let {
            if (it != null) {
                _lastWorkout.value = it
            }
        }
    }

    fun onEvent(event: WorkoutEvent) {
        when (event) {
            is WorkoutEvent.EnteredSets -> {}
            is WorkoutEvent.EnteredReps -> {
                updateReps(event.value)
            }
            is WorkoutEvent.EnteredWeight -> {
                updateWeight(event.value)
            }
            is WorkoutEvent.SaveWorkout -> {
                saveNewWorkout()
            }

            else -> {}
        }
    }

    private fun updateWeight(weight: Double) {
        val updatedWeight = _lastWorkout.value.weight + weight
        _lastWorkout.value = _lastWorkout.value.copy(weight = updatedWeight)
    }

    private fun updateReps(reps: Int) {
        val updatedReps = _lastWorkout.value.rep + reps
        _lastWorkout.value = _lastWorkout.value.copy(rep = updatedReps)
    }

    private fun saveNewWorkout() {
        _lastWorkout.value = _lastWorkout.value.copy(
            id = 0,
            date = LocalDateTime.now()
        )

        viewModelScope.launch {
            try {
                workoutUseCases.insertWorkoutUseCase(_lastWorkout.value)
                loadWorkoutList(workoutName)
            } catch (e: Exception) {
                Log.e(TAG, "Error saving to database. $e")
            }
        }
    }


    private fun loadWorkoutList(workoutName: String) {
        viewModelScope.launch {
            try {
                val workoutList = workoutUseCases.getWorkoutListByName(workoutName).first()
                _state.value = _state.value.copy(
                    workouts = workoutList
                )
                getLastWorkout()
            } catch (e: Exception) {
                // Handle any errors, for example updating the state with error information
                Log.e(TAG, "Error fetching ExerciseList", e)
            }
        }
    }


    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit) {
        if(exerciseListId == -1){
            openAndPopUp(EXERCISE_APP_SCREEN, EXERCISE_LIST_SCREEN)
        }else{
            openAndPopUp("$EXERCISE_LIST_SCREEN/$exerciseListId", WORKOUT_SCREEN)
        }
    }

    fun editWorkoutScreen(openScreen: (String) -> Unit, workoutId: Int) {
        if(exerciseListId == -1){
            openScreen("$EDIT_WORKOUT_SCREEN/$workoutId")
        }else{
            openScreen("$EDIT_WORKOUT_SCREEN/$workoutId/$exerciseListId")
        }
    }

    companion object {
        private const val TAG = "WorkOut ViewModel"
    }
}