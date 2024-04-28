package com.example.workoutlogz.feature_workouts.domain.repository

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout
import kotlinx.coroutines.flow.Flow

interface ExerciseListRepository {
    suspend fun getAllExerciseLists(): Flow<List<ExerciseList>>
    suspend fun insertExerciseList(exerciseList: ExerciseList)
    suspend fun deleteById(exerciseListId: Int)
    suspend fun getAllWorkouts(): Flow<List<Workout>>
    suspend fun getExerciseListById(exerciseListId: Int): Flow<ExerciseList>
    suspend fun upDateExerciseListExerciseNames(exerciseListId: Int, exerciseNames: List<String>)
    suspend fun upDateExerciseList(exerciseList: ExerciseList)
}