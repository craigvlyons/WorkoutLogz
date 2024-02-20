package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

data class ExerciseListUseCases(
    val getAllExerciseListUseCase: GetAllExerciseListUseCase,
    val addExerciseListUseCase: AddExerciseListUseCase,
    val getExerciseListByIdUseCase: GetExerciseListByIdUseCase,
    val updateExerciseListNamesUseCase: UpdateExerciseListNamesUseCase,
    val updateExerciseListUseCase: UpdateExerciseListUseCase
)
