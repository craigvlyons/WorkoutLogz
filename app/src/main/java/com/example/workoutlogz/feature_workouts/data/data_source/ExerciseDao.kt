package com.example.workoutlogz.feature_workouts.data.data_source


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout
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

    @Query("SELECT * FROM exercises WHERE name= :exerciseName")
    fun getExerciseByName(exerciseName: String): Exercise

    @Query("DELETE FROM Exercises WHERE id = :exerciseId")
    suspend fun DeleteById(exerciseId: Int)

    @Query("SELECT COUNT(*) > 0 FROM Exercises WHERE name = :exerciseName")
    suspend fun isExercisesExists(exerciseName: String): Boolean



    // Workout table
    @Insert
    suspend fun insertWorkout(workout: Workout)
    @Query("SELECT * FROM Workout")
    fun getAllWorkouts(): Flow<List<Workout>>




    // Exercise List Table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseList(exerciseList: ExerciseList)

    @Query("SELECT * FROM exerciselist")
    fun getAllExerciseLists(): Flow<List<ExerciseList>>

    @Query("SELECT * FROM ExerciseList WHERE ID = :exerciseListId")
    fun getExerciseListById(exerciseListId: Int): Flow<ExerciseList>

    // Get with Workouts by ExerciseList ID
    @Query("SELECT * FROM Workout WHERE exerciseListId = :exerciseListId")
    fun getWorkoutsForExerciseListId(exerciseListId: Int): Flow<List<Workout>>

    // Update an ExerciseList
    @Update
    suspend fun updateExerciseList(exerciseList: ExerciseList)

    // Delete an ExerciseList by ID (Workouts will be automatically deleted due to the CASCADE rule)
    @Query("DELETE FROM ExerciseList WHERE id = :exerciseListId")
    suspend fun deleteExerciseList(exerciseListId: Int)


}