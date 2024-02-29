package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutByIdUseCase(val repository: WorkoutRepository) {
    operator fun invoke(workoutId: Int): Flow<Workout> = repository.getWorkoutById(workoutId)
}