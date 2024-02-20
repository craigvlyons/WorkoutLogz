package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase

data class ExerciseUseCases(
    val getAllExerciseUseCase: GetAllExerciseUseCase,
    val addNewExerciseUseCase: AddNewExerciseUseCase,
    val deleteExerciseByIdUseCase: DeleteExerciseByIdUseCase,
    val getExerciseByNameUseCase: GetExerciseByNameUseCase,
    val isExerciseExistsUseCase: IsExerciseExistsUseCase
)
