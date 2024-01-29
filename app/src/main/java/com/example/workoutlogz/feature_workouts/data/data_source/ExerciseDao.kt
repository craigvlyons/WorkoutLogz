package com.example.workoutlogz.feature_workouts.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseEvent
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    // Exercise Table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("DELETE FROM Exercises WHERE id = :exerciseId")
    suspend fun DeleteById(exerciseId: Int)



    // Workout table
    @Insert
    suspend fun insertWorkout(workout: Workout)
    @Query("SELECT * FROM Workout")
    fun getAllWorkouts(): Flow<List<Workout>>




    // Exercise List Table
    @Transaction
    @Query("SELECT * FROM ExerciseList WHERE id = :exerciseListId")
    fun getExerciseListWithWorkouts(exerciseListId: Int): List<ExerciseListWithWorkouts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseList(exerciseList: ExerciseList)

    @Query("SELECT * FROM exerciselist")
    fun getAllExerciseLists(): Flow<List<ExerciseList>>



}