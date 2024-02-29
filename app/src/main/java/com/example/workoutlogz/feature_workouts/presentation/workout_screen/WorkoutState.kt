package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import com.example.workoutlogz.feature_workouts.data.models.Workout

data class WorkoutState(
    val workouts: List<Workout>? = null
)