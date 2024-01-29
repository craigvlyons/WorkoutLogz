package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository


class DeleteExerciseByIdUseCase(
    val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Int) = repository.DeleteById(exerciseId)

}