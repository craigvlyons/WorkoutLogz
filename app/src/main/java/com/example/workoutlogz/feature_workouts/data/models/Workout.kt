package com.example.workoutlogz.feature_workouts.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [ForeignKey(entity = ExerciseList::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("exerciseListId"))])
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseListId: Int,
    val name: String = "",
    val set: Int = 0,
    val rep: Int = 0,
    val weight: Double = 0.0,
    val rest: String? = null,
    val date: String = ""
)
