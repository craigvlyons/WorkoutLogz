package com.example.workoutlogz.feature_workouts.domain.repository

import com.example.workoutlogz.feature_workouts.data.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getWorkouts(): Flow<List<Workout>>

}