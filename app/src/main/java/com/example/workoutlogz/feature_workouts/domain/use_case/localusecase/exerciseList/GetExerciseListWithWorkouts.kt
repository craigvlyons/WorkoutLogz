package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListWithWorkoutsRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExerciseListWithWorkouts @Inject constructor(
    private val repository: ExerciseListWithWorkoutsRepos
) {
    operator fun invoke(exerciseListId: Int) : Flow<ExerciseListWithWorkouts> = repository.getExerciseListWithWorkouts(exerciseListId)
}