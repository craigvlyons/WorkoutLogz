package com.example.workoutlogz.feature_workouts.domain.repository


import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import kotlinx.coroutines.flow.Flow

interface ExerciseListWithWorkoutsRepos {
    fun getExerciseListWithWorkouts(exerciseListId: Int): Flow<ExerciseListWithWorkouts>
    suspend fun updateExerciseList(exerciseList: ExerciseList)
    suspend fun deleteExerciseList(exerciseListId: Int)
}