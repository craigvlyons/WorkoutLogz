package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository

class InsertWorkoutUseCase(val repository: WorkoutRepository) {
    suspend operator fun invoke(workout: Workout) = repository.insertWorkout(workout)
}