package com.example.workoutlogz.feature_workouts.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

@Entity
data class ExerciseList(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val description : String = "",
)
