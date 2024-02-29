package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository

class DeleteWorkoutByNameUseCase (val repository: WorkoutRepository) {
    operator fun invoke(workoutName: String) = repository.deleteWorkoutByName(workoutName)
}