package com.example.workoutlogz.feature_workouts.presentation.settings_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(): ViewModel() {
    private val _uiState = mutableStateOf(SettingsUiState())
    val uiState: State<SettingsUiState> = _uiState



//    fun onEvent(event: AddNewExerciseName){
//        when(event){
//            is AddNewExerciseName.EnteredSearch -> {
//                _nameTextField.value =  _nameTextField.value.copy(
//                    text = event.value
//                )
//            }
//            is AddNewExerciseName.ChangeSearchFocus -> {
//                _nameTextField.value = _nameTextField.value.copy(
//                    isHintVisible = !event.focusState.isFocused && _nameTextField.value.text.isBlank()
//                )
//            }
//            is AddNewExerciseName.SaveExercise -> {
//                // save exercise to database...
//
//            }
//            is AddNewExerciseName.AddExercise -> {
//                saveExercise()
//                // reset text box
//                _nameTextField.value = _nameTextField.value.copy(
//                    text = ""
//                )
//            }
//
//            else -> {}
//        }
//    }


    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit ) = openAndPopUp(EXERCISE_APP_SCREEN, SETTINGS_SCREEN)

    fun restartApp(restartApp: (String) -> Unit) = restartApp(SPLASH_SCREEN)

}