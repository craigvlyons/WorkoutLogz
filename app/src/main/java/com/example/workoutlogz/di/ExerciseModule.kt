package com.example.workoutlogz.di

import android.app.Application
import androidx.room.Room
import com.example.workoutlogz.feature_workouts.data.data_source.ExerciseDB
import com.example.workoutlogz.feature_workouts.data.repository.ExerciseListRepositoryImpl
import com.example.workoutlogz.feature_workouts.data.repository.ExerciseRepositoryImpl
import com.example.workoutlogz.feature_workouts.data.repository.WorkoutRepositoryImpl
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseRepository
import com.example.workoutlogz.feature_workouts.domain.repository.WorkoutRepository
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.AddNewExerciseUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.DeleteExerciseByIdUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.ExerciseUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.GetAllExerciseUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.GetExerciseByNameUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exercises.IsExerciseExistsUseCase

import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.AddExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetAllExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetExerciseListByIdUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.UpdateExerciseListNamesUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.UpdateExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.DeleteWorkoutByIdUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.DeleteWorkoutByNameUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.GetAllWorkoutsUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.GetWorkoutByIdUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.GetWorkoutByNameUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.GetWorkoutListByName
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.InsertWorkoutUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.UpdateWorkoutUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.WorkoutUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

//    private val roomCallback = object : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            val exerciseDao = provideExerciseDatabase(Application()).exerciseDao
//            prepopulateDatabase(exerciseDao)
//        }
//    }

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
    fun provideExerciseUseCases(repository: ExerciseRepository) : ExerciseUseCases {
        return ExerciseUseCases(
            getAllExerciseUseCase = GetAllExerciseUseCase(repository),
            addNewExerciseUseCase = AddNewExerciseUseCase(repository),
            deleteExerciseByIdUseCase = DeleteExerciseByIdUseCase(repository),
            getExerciseByNameUseCase = GetExerciseByNameUseCase(repository),
            isExerciseExistsUseCase = IsExerciseExistsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideExerciseListUseCases(repository: ExerciseListRepository) : ExerciseListUseCases{
        return ExerciseListUseCases(
            getAllExerciseListUseCase = GetAllExerciseListUseCase(repository),
            addExerciseListUseCase = AddExerciseListUseCase(repository),
            getExerciseListByIdUseCase = GetExerciseListByIdUseCase(repository),
            updateExerciseListNamesUseCase = UpdateExerciseListNamesUseCase(repository),
            updateExerciseListUseCase = UpdateExerciseListUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideWorkoutUseCase(repository: WorkoutRepository): WorkoutUseCases{
        return WorkoutUseCases(
            insertWorkoutUseCase = InsertWorkoutUseCase(repository),
            updateWorkoutUseCase = UpdateWorkoutUseCase(repository),
            getAllWorkoutsUseCase = GetAllWorkoutsUseCase(repository),
            getWorkoutByIdUseCase = GetWorkoutByIdUseCase(repository),
            getWorkoutByNameUseCase = GetWorkoutByNameUseCase(repository),
            deleteWorkoutByIdUseCase = DeleteWorkoutByIdUseCase(repository),
            deleteWorkoutByNameUseCase = DeleteWorkoutByNameUseCase(repository),
            getWorkoutListByName = GetWorkoutListByName(repository)
        )
    }

}