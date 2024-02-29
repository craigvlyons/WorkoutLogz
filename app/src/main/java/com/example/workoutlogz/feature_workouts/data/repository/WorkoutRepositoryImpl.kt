package com.example.workoutlogz.feature_workouts.data.repository

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutRepositoryImpl(
    private val dao: ExerciseDao
): WorkoutRepository {
    override fun getAllWorkouts(): Flow<List<Workout>> {
        return dao.getAllWorkouts()
    }

    override fun getWorkoutListByName(workoutName: String): Flow<List<Workout>> {
        return dao.getWorkoutListByName(workoutName)
    }

    override suspend fun insertWorkout(workout: Workout) {
        dao.insertWorkout(workout)
    }

    override fun updateWorkout(workout: Workout) {
        dao.updateWorkout(workout)
    }

    override fun getWorkoutById(workoutId: Int): Flow<Workout> {
        return dao.getWorkoutById(workoutId)
    }

    override fun getWorkoutByName(workoutName: String): Flow<Workout> {
        return dao.getWorkoutByName(workoutName)
    }

    override fun deleteWorkoutByName(workoutName: String) {
        dao.deleteWorkoutByName(workoutName)
    }

    override fun deleteWorkoutById(workoutId: Int) {
        dao.deleteWorkoutById(workoutId)
    }

}