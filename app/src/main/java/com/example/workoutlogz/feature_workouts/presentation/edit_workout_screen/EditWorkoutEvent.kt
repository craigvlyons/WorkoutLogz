package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import androidx.compose.ui.focus.FocusState

sealed class EditWorkoutEvent {
    data class EnteredReps(val value:Int): EditWorkoutEvent()
    data class ChangeRepsFocus(val focusState: FocusState): EditWorkoutEvent()
    data class EnteredSets(val value: Double): EditWorkoutEvent()
    data class EnteredWeight(val value: Double): EditWorkoutEvent()
    data class ChangeSetsFocus(val focusState: FocusState): EditWorkoutEvent()
    data class ToggleWorkoutSelection(val workoutName: String) : EditWorkoutEvent()
    data object LoadWorkoutList : EditWorkoutEvent()
    data object UpdateWorkoutList : EditWorkoutEvent()
    data object UpdateWorkout : EditWorkoutEvent()
    data object SaveWorkoutList : EditWorkoutEvent()
    data object SaveWorkout : EditWorkoutEvent()
}
