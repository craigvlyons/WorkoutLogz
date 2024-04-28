package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts

data class ExerciseListState(
    var exerciseNamesList: List<String> = emptyList(),
    var exerciseList: ExerciseList? = null,
)