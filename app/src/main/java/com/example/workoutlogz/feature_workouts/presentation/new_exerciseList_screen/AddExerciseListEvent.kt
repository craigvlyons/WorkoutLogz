package com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen

import androidx.compose.ui.focus.FocusState

sealed class AddExerciseListEvent{
    data class EnteredTitle(val value:String): AddExerciseListEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddExerciseListEvent()
    data class EnteredDescription(val value:String): AddExerciseListEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddExerciseListEvent()

    data object AddAddExerciseList: AddExerciseListEvent()
}