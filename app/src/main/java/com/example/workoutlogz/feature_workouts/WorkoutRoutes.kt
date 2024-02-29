package com.example.workoutlogz.feature_workouts

import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseEvent

const val SPLASH_SCREEN = "SplashScreen"
const val SETTINGS_SCREEN = "SettingsScreen"
const val EDIT_WORKOUT_SCREEN = "EditWorkoutScreen"
const val STATS_SCREEN = "StatsScreen"
const val WORKOUT_SCREEN = "WorkoutScreen"
const val EXERCISE_APP_SCREEN = "ExerciseAppScreen"
const val ADD_EXERCISE_NAME_SCREEN = "AddExerciseNamesScreen"
const val NEW_EXERCISE_LIST_SCREEN = "NewExerciseListScreen"
const val EDIT_EXERCISE_LIST_SCREEN = "EditExerciseListScreen"
const val EXERCISE_LIST_SCREEN = "ExerciseListScreen"

const val EXERCISELIST_ID = "exerciseListId"
const val EXERCISELIST_ID_ARG = "?$EXERCISELIST_ID={$EXERCISELIST_ID}"
const val WORKOUT_NAME = "workoutName"
const val WORKOUT_NAME_ARG = "?$WORKOUT_NAME={$WORKOUT_NAME}&$EXERCISELIST_ID={$EXERCISELIST_ID}"

const val WORKOUT_SCREEN_ROUTE = "$WORKOUT_SCREEN/{$WORKOUT_NAME}/{$EXERCISELIST_ID}"

