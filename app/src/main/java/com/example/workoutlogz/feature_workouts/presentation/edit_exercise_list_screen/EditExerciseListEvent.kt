package com.example.workoutlogz.feature_workouts.presentation.edit_exercise_list_screen

import androidx.compose.ui.focus.FocusState

sealed class EditExerciseListEvent {
    data class EnteredTitle(val value:String): EditExerciseListEvent()
    data class ChangeTitleFocus(val focusState: FocusState): EditExerciseListEvent()
    data class EnteredDescription(val value:String): EditExerciseListEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): EditExerciseListEvent()
    data class ToggleExerciseSelection(val exerciseName: String): EditExerciseListEvent()
    data object LoadExerciseList: EditExerciseListEvent()
    data object UpdateExerciseList: EditExerciseListEvent()
    data object DeleteExerciseList: EditExerciseListEvent()
    data object SaveExerciseList: EditExerciseListEvent()
}