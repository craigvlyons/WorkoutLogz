package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetAllWorkoutsUseCase (val repository: WorkoutRepository) {
    operator fun invoke(): Flow<List<Workout>> = repository.getAllWorkouts()
}