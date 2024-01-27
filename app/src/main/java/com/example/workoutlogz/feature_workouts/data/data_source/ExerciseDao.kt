package com.example.workoutlogz.feature_workouts.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.data.models.Workout


@Dao
interface ExerciseDao {
    @Insert
    fun insertExerciseList(exerciseList: ExerciseList)

    @Insert
    fun insertWorkout(workout: Workout)

    @Transaction
    @Query("SELECT * FROM ExerciseList WHERE id = :exerciseListId")
    fun getExerciseListWithWorkouts(exerciseListId: Int): List<ExerciseListWithWorkouts>


    // list of exercise names
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): LiveData<List<Exercise>>
}