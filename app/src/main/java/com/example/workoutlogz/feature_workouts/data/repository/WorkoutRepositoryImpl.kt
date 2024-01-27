package com.example.workoutlogz.feature_workouts.data.repository

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutRepositoryImpl(
    private val dao: ExerciseDao
): WorkoutRepository {
    override fun getWorkouts(): Flow<List<Workout>> {
        return dao.getAllWorkouts()
    }

}