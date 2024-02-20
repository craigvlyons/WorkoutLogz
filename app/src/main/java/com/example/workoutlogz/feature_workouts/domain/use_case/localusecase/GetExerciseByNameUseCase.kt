package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository

class GetExerciseByNameUseCase(private val repository: ExerciseRepository) {
    suspend operator fun invoke(exerciseName: String): Exercise = repository.getExerciseByName(exerciseName)
}