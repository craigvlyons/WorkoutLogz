package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetAllExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke( ): Flow<List<Exercise>> = repository.getAllExercises()
}