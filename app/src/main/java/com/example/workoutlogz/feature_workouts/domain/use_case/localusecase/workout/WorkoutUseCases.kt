package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

data class WorkoutUseCases(
    val insertWorkoutUseCase: InsertWorkoutUseCase,
    val updateWorkoutUseCase: UpdateWorkoutUseCase,
    val getAllWorkoutsUseCase: GetAllWorkoutsUseCase,
    val getWorkoutByIdUseCase: GetWorkoutByIdUseCase,
    val getWorkoutByNameUseCase: GetWorkoutByNameUseCase,
    val deleteWorkoutByIdUseCase: DeleteWorkoutByIdUseCase,
    val deleteWorkoutByNameUseCase: DeleteWorkoutByNameUseCase
)