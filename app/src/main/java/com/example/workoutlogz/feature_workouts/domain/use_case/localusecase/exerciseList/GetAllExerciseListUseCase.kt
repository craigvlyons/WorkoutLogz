package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import kotlinx.coroutines.flow.Flow

class GetAllExerciseListUseCase(
    private val repository: ExerciseListRepository) {
    suspend operator fun invoke( ): Flow<List<ExerciseList>> = repository.getAllExerciseLists()
}