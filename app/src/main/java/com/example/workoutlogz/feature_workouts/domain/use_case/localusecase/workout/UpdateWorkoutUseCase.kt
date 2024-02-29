package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout

import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository

class UpdateWorkoutUseCase(val repository: WorkoutRepository) {
    operator fun invoke(workout: Workout) = repository.updateWorkout(workout)
}