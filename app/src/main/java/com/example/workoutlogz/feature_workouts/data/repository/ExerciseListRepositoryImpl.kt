package com.example.workoutlogz.feature_workouts.data.repository

import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import kotlinx.coroutines.flow.Flow

class ExerciseListRepositoryImpl(private val dao: ExerciseDao): ExerciseListRepository {
    override suspend fun getAllExerciseLists(): Flow<List<ExerciseList>> {
        return dao.getAllExerciseLists()
    }

    override suspend fun insertExerciseList(exerciseList: ExerciseList) {
        dao.insertExerciseList(exerciseList)
    }

    override suspend fun deleteById(exerciseListId: Int) {
        dao.DeleteById(exerciseListId)
    }

    override suspend fun getAllWorkouts(): Flow<List<Workout>> {
        return dao.getAllWorkouts()
    }

    override suspend fun getExerciseListById(exerciseListId: Int): Flow<ExerciseList> {
       return dao.getExerciseListById(exerciseListId)
    }


    override suspend fun getAllWorkoutsInExerciseList(exerciseListId: Int): Flow<List<Workout>> {
        return dao.getWorkoutsForExerciseListId(exerciseListId)
    }
}