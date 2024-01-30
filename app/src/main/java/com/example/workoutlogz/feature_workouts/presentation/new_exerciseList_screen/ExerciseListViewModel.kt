package com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EXERCISE_APP_SCREEN
import com.example.workoutlogz.feature_workouts.NEW_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.SPLASH_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.WorkoutUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.AddExerciseListUseCase
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseListUseCase: ExerciseListUseCases
): ViewModel() {
    private val _titleTextField = mutableStateOf(BasicTextFieldState(
        hint = "Exercise List Title..."
    ))
    private val _descriptionTextField = mutableStateOf(BasicTextFieldState(
        hint = "Exercise List Description..."
    ))

    val title: State<BasicTextFieldState> = _titleTextField
    val description: State<BasicTextFieldState> = _descriptionTextField

    fun onEvent(event: ExerciseListEvent){
        when(event){
            is ExerciseListEvent.EnteredTitle -> {
                _titleTextField.value = _titleTextField.value.copy(
                    text = event.value
                )
            }
            is ExerciseListEvent.EnteredDescription -> {
                _descriptionTextField.value = _descriptionTextField.value.copy(
                    text = event.value
                )
            }
            is ExerciseListEvent.ChangeTitleFocus -> {
                _titleTextField.value = _titleTextField.value.copy(
                    isHintVisible = !event.focusState.isFocused && _titleTextField.value.text.isBlank()
                )
            }
            is ExerciseListEvent.ChangeDescriptionFocus -> {
                _descriptionTextField.value = _descriptionTextField.value.copy(
                    isHintVisible = !event.focusState.isFocused && _descriptionTextField.value.text.isBlank()
                )
            }
            is ExerciseListEvent.AddExerciseList -> {
                addExerciseList()
            }
            else -> {}
        }
    }

private fun addExerciseList(){
    if(_titleTextField.value.text.isBlank() || _descriptionTextField.value.text.isBlank()){
        Log.i(TAG, "text is blank")
        return
    }
    val exerciseList = ExerciseList(
        name = _titleTextField.value.text,
        description = _descriptionTextField.value.text
    )
    viewModelScope.launch {
        try {
            Log.i(TAG, "saving exercise ${exerciseList.name}")
            exerciseListUseCase.addExerciseListUseCase(exerciseList)
            _titleTextField.value = _titleTextField.value.copy(
                text = "",
                isHintVisible = true
            )
            _descriptionTextField.value = _descriptionTextField.value.copy(
                text = "",
                isHintVisible = true
            )
        }catch (ex: Exception){
            Log.i(TAG,"Failed to save exercise List")
            Log.e(TAG, ex.toString())
        }
        Log.i(TAG, "saved")
    }


}

    // Navigation
    fun navBack(openAndPopUp: (String, String) -> Unit ) = openAndPopUp(EXERCISE_APP_SCREEN, NEW_EXERCISE_LIST_SCREEN)

    fun restartApp(restartApp: (String) -> Unit) = restartApp(SPLASH_SCREEN)

    /*
        Verbose: Log.v(tag, message)
        Debug: Log.d(tag, message)
        Info: Log.i(tag, message)
        Warning: Log.w(tag, message)
        Error: Log.e(tag, message)
     */
    companion object {
        private const val TAG = "Add Exercise List"
    }




}