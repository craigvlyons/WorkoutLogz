package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetAllExerciseListUseCase

data class WorkoutUseCases(
    val getAllExerciseUseCase: GetAllExerciseUseCase,
    val addNewExerciseUseCase: AddNewExerciseUseCase,
    val deleteExerciseByIdUseCase: DeleteExerciseByIdUseCase
)
