package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workoutlogz.feature_workouts.EDIT_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(WorkoutState())
    val state: StateFlow<WorkoutState> = _state


    val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1




    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit) =
        openAndPopUp(
            "$EXERCISE_LIST_SCREEN/$exerciseListId"
            , WORKOUT_SCREEN
        )

    companion object {
        private const val TAG = "Edit Exercise List"
    }
}