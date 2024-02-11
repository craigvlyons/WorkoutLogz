package com.example.workoutlogz.feature_workouts.presentation.exercise_app

sealed class ExerciseEvent {
    data class NavigateSettings(val screenName: (String) -> Unit): ExerciseEvent()
    data class ExerciseListId(val exerciseId: (Int) ): ExerciseEvent()
    data class DeleteById(val exerciseId: (Int) ): ExerciseEvent()
}