package com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen

import androidx.compose.ui.focus.FocusState

sealed class ExerciseListEvent{
    data class EnteredTitle(val value:String): ExerciseListEvent()
    data class ChangeTitleFocus(val focusState: FocusState): ExerciseListEvent()
    data class EnteredDescription(val value:String): ExerciseListEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): ExerciseListEvent()

    data object AddExerciseList: ExerciseListEvent()
}