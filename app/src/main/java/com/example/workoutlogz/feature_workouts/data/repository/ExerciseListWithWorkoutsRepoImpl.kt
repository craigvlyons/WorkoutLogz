package com.example.workoutlogz.feature_workouts.data.repository

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListWithWorkoutsRepos

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseListWithWorkoutsRepoImpl (
    private val dao: ExerciseDao
): ExerciseListWithWorkoutsRepos {
    override fun getExerciseListWithWorkouts(exerciseListId: Int): Flow<ExerciseListWithWorkouts> {
        return dao.getExerciseListWithWorkouts(exerciseListId)
    }

    override suspend fun updateExerciseList(exerciseList: ExerciseList) {
        dao.updateExerciseList(exerciseList)
    }

    override suspend fun deleteExerciseList(exerciseListId: Int) {
        TODO("Not yet implemented")
    }

}