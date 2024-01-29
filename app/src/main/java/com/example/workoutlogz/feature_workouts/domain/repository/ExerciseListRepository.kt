package com.example.workoutlogz.feature_workouts.domain.repository

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import kotlinx.coroutines.flow.Flow

interface ExerciseListRepository {
    suspend fun getAllExerciseLists(): Flow<List<ExerciseList>>
    suspend fun insertExerciseList(exercise: ExerciseList)
    suspend fun deleteById(exerciseListId: Int)
}