package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises

import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository

class IsExerciseExistsUseCase(private val repository: ExerciseRepository) {
    suspend operator fun invoke(exerciseName: String): Boolean = repository.isExerciseExists(exerciseName)
}