package com.example.workoutlogz.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDB
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDao
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.repository.ExerciseListRepositoryImpl
import com.example.workoutlogz.feature_workouts.data.repository.ExerciseRepositoryImpl
import com.example.workoutlogz.feature_workouts.data.repository.WorkoutRepositoryImpl
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.AddNewExerciseUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.DeleteExerciseByIdUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.ExerciseUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.GetAllExerciseUseCase

import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.AddExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetAllExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetExerciseListWithWorkouts
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
        )
            .fallbackToDestructiveMigration()
        .build()
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

    // Repositories
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

    @Provides
    @Singleton
    fun providesExerciseListRepository(db: ExerciseDB): ExerciseListRepository{
        return ExerciseListRepositoryImpl(db.exerciseDao)
    }

    // UseCases
    @Provides
    @Singleton
    fun provideExerciseUseCases(repository: ExerciseRepository) : ExerciseUseCases{
        return ExerciseUseCases(
            getAllExerciseUseCase = GetAllExerciseUseCase(repository),
            addNewExerciseUseCase = AddNewExerciseUseCase(repository),
            deleteExerciseByIdUseCase = DeleteExerciseByIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideExerciseListUseCases(repository: ExerciseListRepository) : ExerciseListUseCases{
        return ExerciseListUseCases(
            getAllExerciseListUseCase = GetAllExerciseListUseCase(repository),
            addExerciseListUseCase = AddExerciseListUseCase(repository)
        )
    }


}