package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import androidx.lifecycle.ViewModel
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class ExerciseAppViewModel @Inject constructor() : ViewModel() {

    /* TODO
        create the use cases.
        get last exercise summery - use the last date
        list of all the exercise names
        display each exercise list


     */
    fun onEvent(event: ExerciseEvent){
        when(event){

        is ExerciseEvent.NavigateSettings -> {
            onSettingsClick { event.screenName }
        }
        }
    }


    // Navigation functions...
    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

}