package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList

data class ExerciseAppState(
    val exercises: List<Exercise> = emptyList(),
    val exerciseList: List<ExerciseList> = emptyList()
)

