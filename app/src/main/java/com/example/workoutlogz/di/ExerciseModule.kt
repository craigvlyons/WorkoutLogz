package com.example.workoutlogz.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutlogz.feature_workouts.WorkoutApp
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDB
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.repository.ExerciseRepositoryImpl
import com.example.workoutlogz.feature_workouts.data.repository.WorkoutRepositoryImpl
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.GetAllExerciseUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.WorkoutUseCases
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

    @Provides
    @Singleton
    fun provideWorkoutRepository(db: ExerciseDB) : WorkoutRepository{
        return WorkoutRepositoryImpl(db.exerciseDao)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(db: ExerciseDB) : ExerciseRepository{
        return ExerciseRepositoryImpl(db.exerciseDao)
    }

    // UseCases
    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: ExerciseRepository) : WorkoutUseCases{
        return WorkoutUseCases(
            getAllExerciseUseCase = GetAllExerciseUseCase(repository)
        )
    }
}