package com.example.workoutlogz.feature_workouts.presentation.settings_screen

import androidx.lifecycle.ViewModel
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(): ViewModel() {


    fun navBack(openAndPopUp: (String, String) -> Unit ) = openAndPopUp(EXERCISE_APP_SCREEN, SETTINGS_SCREEN)

    fun restartApp(restartApp: (String) -> Unit) = restartApp(SPLASH_SCREEN)

}