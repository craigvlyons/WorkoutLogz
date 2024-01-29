package com.example.workoutlogz.feature_workouts.presentation.add_workout_names

import com.example.workoutlogz.feature_workouts.data.models.Exercise

data class AddExerciseNamesState(
    val exercise: Exercise? = null,
    val exerciseList: List<Exercise> = emptyList()
)
