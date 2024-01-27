package com.example.workoutlogz.feature_workouts.data.models

import androidx.room.Embedded
import androidx.room.Relation


data class ExerciseListWithWorkouts(
    @Embedded val exerciseList: ExerciseList,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseListId"
    )
    val workouts: List<Workout>
)
