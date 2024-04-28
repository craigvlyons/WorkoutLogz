package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EDIT_WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.WORKOUT_ID
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.WorkoutUseCases
import com.example.workoutlogz.feature_workouts.presentation.workout_screen.WorkoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {
private val _state = MutableStateFlow(EditWorkoutState())
    val state: StateFlow<EditWorkoutState> = _state

    val workoutId: Int = savedStateHandle[WORKOUT_ID]?: -1
    private val _workoutName = mutableStateOf("")

    val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1
    //private val _exerciseListId = mutableStateOf("")

    val workoutName: State<String> = _workoutName


init {
    loadWorkout(workoutId)
    Log.i(TAG, "exercise list id: $exerciseListId")
}

    private fun loadWorkout(workoutId: Int) {
        viewModelScope.launch {
            workoutUseCases.getWorkoutByIdUseCase(workoutId).collect { workout ->
                _state.value = _state.value.copy(
                    workout = workout,
                )
                _workoutName.value = state.value.workout?.name ?: ""
            }
        }

    }



    fun navBack(openAndPopUp: (String, String) -> Unit) = openAndPopUp("$WORKOUT_SCREEN/${workoutName.value}/$exerciseListId", EDIT_WORKOUT_SCREEN)




    companion object {
        private const val TAG = "EditWorkOut ViewModel"
    }
}