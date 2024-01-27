package com.example.workoutlogz.feature_workouts.data.repository

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImpl(private val dao: ExerciseDao): ExerciseRepository {
    override suspend fun getAllExercises(): Flow<List<Exercise>> {
        return dao.getAllExercises()
    }

    override suspend fun insertExercise(exercise: Exercise) {
        dao.insertExercise(exercise)
    }

    override suspend fun insertAllExercises(exercises: List<Exercise>) {
        dao.insertAll(exercises)
    }

}