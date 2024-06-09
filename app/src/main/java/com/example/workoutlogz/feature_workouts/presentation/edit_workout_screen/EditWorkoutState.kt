package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import com.example.workoutlogz.feature_workouts.data.models.Workout
import java.time.LocalDateTime


data class EditWorkoutState (
    val workout: Workout? = null,
    val date: LocalDateTime = LocalDateTime.MIN
)