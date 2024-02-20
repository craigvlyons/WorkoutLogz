package com.example.workoutlogz.feature_workouts.domain.repository

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun getAllExercises(): Flow<List<Exercise>>
    suspend fun insertExercise(exercise: Exercise)
    suspend fun insertAllExercises(exercises: List<Exercise>)
    suspend fun DeleteById(exerciseId: Int)
    suspend fun isExerciseExists(exerciseName: String) :Boolean
    suspend fun getExerciseByName(exerciseName: String) :Exercise

}