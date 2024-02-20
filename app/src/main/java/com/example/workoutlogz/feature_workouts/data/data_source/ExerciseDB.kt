package com.example.workoutlogz.feature_workouts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogz.feature_workouts.data.models.Converters
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout

@Database(entities = [ExerciseList::class, Workout::class, Exercise::class], version = 1)
@TypeConverters(Converters::class)
abstract class ExerciseDB: RoomDatabase() {
    abstract val exerciseDao: ExerciseDao

    companion object{
        const val DATABASE_NAME = "exercise_db"
    }
}