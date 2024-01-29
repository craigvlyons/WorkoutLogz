package com.example.workoutlogz.feature_workouts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout

@Database(entities = [ExerciseList::class, Workout::class, Exercise::class], version = 3)
abstract class ExerciseDB: RoomDatabase() {
    abstract val exerciseDao: ExerciseDao

    companion object{
        const val DATABASE_NAME = "exercise_db"
    }
}