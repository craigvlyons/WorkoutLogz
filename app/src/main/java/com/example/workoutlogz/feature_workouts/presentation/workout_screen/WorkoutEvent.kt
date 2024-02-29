package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import androidx.compose.ui.focus.FocusState
import com.example.workoutlogz.feature_workouts.presentation.edit_exercise_list_screen.EditExerciseListEvent
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListEvent

sealed class WorkoutEvent {
    data class EnteredReps(val value:String): WorkoutEvent()
    data class ChangeRepsFocus(val focusState: FocusState): WorkoutEvent()
    data class EnteredSets(val value:String): WorkoutEvent()
    data class ChangeSetsFocus(val focusState: FocusState): WorkoutEvent()
    data class ToggleWorkoutSelection(val workoutName: String) : WorkoutEvent()
    data object LoadWorkoutList : WorkoutEvent()
    data object UpdateWorkoutList : WorkoutEvent()
    data object UpdateWorkout : WorkoutEvent()
    data object SaveWorkoutList : WorkoutEvent()
    data object SaveWorkout : WorkoutEvent()
}