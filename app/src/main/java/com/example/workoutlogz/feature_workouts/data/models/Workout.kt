package com.example.workoutlogz.feature_workouts.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val set: Int = 0,
    val rep: Int = 0,
    val weight: Double = 0.0,
    val rest: String? = null,
    val date: LocalDateTime,
    val note: String?
)
