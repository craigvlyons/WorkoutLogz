package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogz.feature_workouts.ADD_EXERCISE_NAME_SCREEN
import com.example.workoutlogz.feature_workouts.EXERCISELIST_ID
import com.example.workoutlogz.feature_workouts.SETTINGS_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.ExerciseUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.ExerciseListUseCases
import com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList.GetExerciseListWithWorkouts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
* on this screen we will want to display the exercise list and all workouts.
* be able to add workouts to the exercise list.
* a dropdown to add more workouts.
*
 */

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val exerciseListUseCases: ExerciseListUseCases,
    private val getExerciseListWithWorkouts: GetExerciseListWithWorkouts ,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _state = MutableStateFlow(ExerciseListState())
    val state: StateFlow<ExerciseListState> = _state

    init {
        val exerciseListId: Int = savedStateHandle.get(EXERCISELIST_ID) ?: -1

        //TODO get exercise list by Id
        // Get workouts with exercise list Id
        // list all workout names, as radio buttons

        Log.i(TAG, "exercise id: $exerciseListId")

         if (exerciseListId != -1)  {
            viewModelScope.launch {
                try {
                    getExerciseListWithWorkouts.invoke(exerciseListId).collect { exerciseListWithWorkouts ->
                        // Update the whole state at once
                        _state.value =
                            _state.value.copy(exerciseListWithWorkouts = exerciseListWithWorkouts)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error fetching ExerciseListWithWorkouts", e)
                    // Handle error, maybe update the state with an error message
                }

                // _state.value.exerciseList = null

                Log.i(TAG, "${_state.value.exerciseList?.name}")
            }
        }
}


    // Navigation functions...
    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onExerciseClick(openScreen: (String) -> Unit) = openScreen(ADD_EXERCISE_NAME_SCREEN)

    companion object {
        private const val TAG = "ExerciseListViewModel"
    }

}