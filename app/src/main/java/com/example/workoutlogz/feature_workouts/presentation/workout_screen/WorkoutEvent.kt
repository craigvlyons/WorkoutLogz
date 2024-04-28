package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import androidx.compose.ui.focus.FocusState

sealed class WorkoutEvent {
    data class EnteredReps(val value:Int): WorkoutEvent()
    data class ChangeRepsFocus(val focusState: FocusState): WorkoutEvent()
    data class EnteredSets(val value: Double): WorkoutEvent()
    data class EnteredWeight(val value: Double): WorkoutEvent()
    data class ChangeSetsFocus(val focusState: FocusState): WorkoutEvent()
    data class ToggleWorkoutSelection(val workoutName: String) : WorkoutEvent()
    data object LoadWorkoutList : WorkoutEvent()
    data object UpdateWorkoutList : WorkoutEvent()
    data object UpdateWorkout : WorkoutEvent()
    data object SaveWorkoutList : WorkoutEvent()
    data object SaveWorkout : WorkoutEvent()
}