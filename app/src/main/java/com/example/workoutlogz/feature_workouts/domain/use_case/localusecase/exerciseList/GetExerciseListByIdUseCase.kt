package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import kotlinx.coroutines.flow.Flow

class GetExerciseListByIdUseCase(val repository: ExerciseListRepository) {
    suspend operator fun invoke(exerciseListId: Int) : Flow<ExerciseList> = repository.getExerciseListById(exerciseListId)
}