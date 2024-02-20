package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository

class UpdateExerciseListNamesUseCase(private val repository: ExerciseListRepository) {
    suspend operator fun invoke(exerciseListId: Int, exerciseNames: List<String> ) = repository.upDateExerciseListExerciseNames(exerciseListId, exerciseNames)
}