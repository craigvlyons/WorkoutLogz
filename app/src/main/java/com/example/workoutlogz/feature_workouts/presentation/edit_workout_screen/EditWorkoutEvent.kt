package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import androidx.compose.ui.focus.FocusState
import java.time.LocalDateTime

sealed class EditWorkoutEvent {
    data class EnteredReps(val value:String): EditWorkoutEvent()
    data class EnteredSets(val value: String): EditWorkoutEvent()
    data class EnteredWeight(val value: String): EditWorkoutEvent()
    data class EnteredNote(val value: String): EditWorkoutEvent()
    data class ChangeRepsFocus(val focusState: FocusState): EditWorkoutEvent()
    data class ChangeWeightFocus(val focusState: FocusState): EditWorkoutEvent()
    data class ChangeNoteFocus(val focusState: FocusState): EditWorkoutEvent()
    data class ToggleWorkoutSelection(val workoutName: String) : EditWorkoutEvent()
    data class ChangeDate(val date: LocalDateTime) : EditWorkoutEvent()
    data object LoadWorkoutList : EditWorkoutEvent()
    data object UpdateWorkoutList : EditWorkoutEvent()
    data object UpdateWorkout : EditWorkoutEvent()
    data object SaveWorkoutList : EditWorkoutEvent()
    data object SaveWorkout : EditWorkoutEvent()
    data object DeleteWorkout: EditWorkoutEvent()
}
