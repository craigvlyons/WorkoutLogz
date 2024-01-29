package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository

class AddExerciseListUseCase(private val repository: ExerciseListRepository) {
    suspend operator fun invoke(exerciseList: ExerciseList) = repository.insertExerciseList(exerciseList)
}