package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

sealed class ExerciseListEvent {
    data class ToggleExerciseSelection(val exerciseName: String) : ExerciseListEvent()
    object LoadExerciseList : ExerciseListEvent()

}