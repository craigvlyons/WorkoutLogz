package com.example.workoutlogz.feature_workouts.presentation.splashScreen

import androidx.compose.runtime.mutableStateOf
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.WorkoutViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : WorkoutViewModel() {
    val showError = mutableStateOf(false)

    init {
        // launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        showError.value = false
        openAndPopUp(EXERCISE_APP_SCREEN, SPLASH_SCREEN)

    }

}
