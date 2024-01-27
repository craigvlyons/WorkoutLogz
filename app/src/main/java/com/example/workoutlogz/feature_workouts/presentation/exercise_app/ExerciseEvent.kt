package com.example.workoutlogz.feature_workouts.presentation.exercise_app

sealed class ExerciseEvent {
    data class NavigateSettings(val screenName: (String) -> Unit): ExerciseEvent()
}