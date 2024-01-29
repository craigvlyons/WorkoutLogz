package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository

class AddNewExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) = repository.insertExercise(exercise)

}