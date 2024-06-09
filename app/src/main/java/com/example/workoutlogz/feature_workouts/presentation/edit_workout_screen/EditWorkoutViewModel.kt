package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.EDIT_WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.WORKOUT_ID
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.workout.WorkoutUseCases
import com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen.BasicTextFieldState
import com.example.workoutlogz.feature_workouts.presentation.workout_screen.WorkoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class EditWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {
@RequiresApi(Build.VERSION_CODES.O)
private val _state = MutableStateFlow(EditWorkoutState(date = LocalDateTime.now()))
    @RequiresApi(Build.VERSION_CODES.O)
    val state: StateFlow<EditWorkoutState> = _state

    val workoutId: Int = savedStateHandle[WORKOUT_ID]?: -1
    private val _workoutName = mutableStateOf("")

    val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1
    //private val _exerciseListId = mutableStateOf("")

    val workoutName: State<String> = _workoutName
    private val _repsText =  mutableStateOf(BasicTextFieldState(
        hint = "reps"
    ))
    private val _weightText =  mutableStateOf(BasicTextFieldState(
        hint = "weight"
    ))
    private val _notesText =  mutableStateOf(BasicTextFieldState(
        hint = "note"
    ))
    val repsText: State<BasicTextFieldState> = _repsText
    val weightText: State<BasicTextFieldState> = _weightText
    val notesText: State<BasicTextFieldState> = _notesText

init {
    loadWorkout(workoutId)
    Log.i(TAG, "exercise list id: $exerciseListId")
}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadWorkout(workoutId: Int) {
        viewModelScope.launch {
            workoutUseCases.getWorkoutByIdUseCase(workoutId).collect { workout ->
                _state.value = _state.value.copy(
                    workout = workout,
                    date = workout.date ?: LocalDateTime.now()
                )
                _workoutName.value = state.value.workout?.name ?: ""
                _repsText.value = _repsText.value.copy(
                    text =  state.value.workout?.rep.toString() ?: "0",
                    isHintVisible = state.value.workout?.rep.toString().isBlank()
                )
                _weightText.value = _weightText.value.copy(
                    text = state.value.workout?.weight.toString() ?: "0.0",
                    isHintVisible = state.value.workout?.weight.toString().isBlank()
                )
                _notesText.value = _notesText.value.copy(
                    text = state.value.workout?.note.toString() ?: "",
                    isHintVisible = state.value.workout?.note.toString().isBlank()
                )

            }
        }
    }

    fun onEvent (event: EditWorkoutEvent){
        when(event){
            is EditWorkoutEvent.EnteredReps -> {
                _repsText.value = _repsText.value.copy(
                    text = event.value
                )
            }
            is EditWorkoutEvent.ChangeRepsFocus -> {
                Log.i(TAG, "event: $event")
                _repsText.value = _repsText.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _repsText.value.text.isBlank()
                )
            }
            is EditWorkoutEvent.EnteredWeight -> {
                _weightText.value = _weightText.value.copy(
                    text = event.value
                )
            }
            is EditWorkoutEvent.EnteredNote -> {
                _notesText.value = _notesText.value.copy(
                    text = event.value
                )
            }
            is EditWorkoutEvent.EnteredSets -> TODO()
            EditWorkoutEvent.LoadWorkoutList -> TODO()
            EditWorkoutEvent.SaveWorkout -> TODO()
            EditWorkoutEvent.SaveWorkoutList -> TODO()
            is EditWorkoutEvent.ToggleWorkoutSelection -> TODO()
            EditWorkoutEvent.UpdateWorkout -> TODO()
            EditWorkoutEvent.UpdateWorkoutList -> TODO()
            EditWorkoutEvent.DeleteWorkout -> deleteWorkout()
            is EditWorkoutEvent.ChangeNoteFocus -> {
                Log.i(TAG, "event: $event")
                _notesText.value = _notesText.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _notesText.value.text.isBlank()
                )
            }
            is EditWorkoutEvent.ChangeWeightFocus -> {
                Log.i(TAG, "event: $event")
                _weightText.value = _weightText.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _weightText.value.text.isBlank()
                )
            }

            is EditWorkoutEvent.ChangeDate -> {
                _state.value = _state.value.copy(date = event.date)
            }
        }
    }

    private fun deleteWorkout() {
//        viewModelScope.launch {
//            workoutUseCases.deleteWorkoutByIdUseCase.invoke(workoutId)
//        }
    }


    fun navBack(openAndPopUp: (String, String) -> Unit) = openAndPopUp("$WORKOUT_SCREEN/${workoutName.value}/$exerciseListId", EDIT_WORKOUT_SCREEN)




    companion object {
        private const val TAG = "EditWorkOut"
    }
}