package com.example.workoutlogz.feature_workouts.presentation.add_workout_names

import androidx.compose.ui.focus.FocusState
import com.example.workoutlogz.feature_workouts.data.models.Exercise

sealed class AddNewExerciseName {
    data class EnteredSearch(val value:String): AddNewExerciseName()
    data class ChangeSearchFocus(val focusState: FocusState): AddNewExerciseName()
    data class SelectedResult(val exerciseResult: Exercise): AddNewExerciseName()
    data class SaveExercise(val exerciseResult: Exercise): AddNewExerciseName()
    data class DeleteExerciseById(val exerciseId: Int): AddNewExerciseName()

    data object AddExercise: AddNewExerciseName()
}