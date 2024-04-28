package com.example.workoutlogz.feature_workouts.domain.repository

import com.example.workoutlogz.feature_workouts.data.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getAllWorkouts(): Flow<List<Workout>>
    fun getWorkoutListByName(workoutName: String): Flow<List<Workout>>
    suspend fun insertWorkout(workout: Workout)
    fun updateWorkout(workout: Workout)
    fun getWorkoutById(workoutId: Int): Flow<Workout>
    fun getWorkoutByName(workoutName: String): Flow<Workout>
    fun deleteWorkoutByName(workoutName: String)
    fun deleteWorkoutById(workoutId: Int)



}