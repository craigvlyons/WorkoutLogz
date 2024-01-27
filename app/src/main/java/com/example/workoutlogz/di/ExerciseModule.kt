package com.example.workoutlogz.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutlogz.feature_workouts.WorkoutApp
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDB
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExerciseModule {
    @Provides
    @Singleton
    fun provideExerciseDatabase(app: Application): ExerciseDB{
        return Room.databaseBuilder(
            app,
            ExerciseDB::class.java,
            ExerciseDB.DATABASE_NAME
        ).build()
    }
    private fun prepopulateDatabase(exerciseDao: ExerciseDao) {
        CoroutineScope(Dispatchers.IO).launch {
            val exercises = listOf(
                Exercise(name = "Push-ups"),
                Exercise(name = "Squats"),
                // ... add all other exercises ...
            )
            exerciseDao.insertAll(exercises)
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val exerciseDao = provideExerciseDatabase(Application()).exerciseDao
            prepopulateDatabase(exerciseDao)
        }
    }

}