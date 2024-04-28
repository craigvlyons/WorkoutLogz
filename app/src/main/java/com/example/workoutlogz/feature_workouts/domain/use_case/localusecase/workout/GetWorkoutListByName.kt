package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutListByName(val repository: WorkoutRepository) {
        operator fun invoke(workoutName: String): Flow<List<Workout>> = repository.getWorkoutListByName(workoutName)
}