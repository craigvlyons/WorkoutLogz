package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository

class DeleteWorkoutByIdUseCase(val repository: WorkoutRepository) {
    operator fun invoke(workoutId: Int) = repository.deleteWorkoutById(workoutId)
}