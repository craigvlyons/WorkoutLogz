package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_TextButtonTitleTextButton
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListViewModel

@Composable
fun EditWorkoutScreen(
    openAndPopUp: (String, String) -> Unit,
    workoutId: Int = -1,
    exerciseListId: Int = -1,
    viewModel: EditWorkoutViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    EditWorkoutContent(
        state = state,
        onBack = { viewModel.navBack(openAndPopUp)},
        saveAndExit = {}
        )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditWorkoutContent(
state: EditWorkoutState,
onBack: () -> Unit,
saveAndExit: () -> Unit
){
    Scaffold(
        topBar = {
            state.workout?.let {
                TopToolbar_TextButtonTitleTextButton(
                    modifier = Modifier,
                    title = it.name,
                    primaryAction = { onBack() },
                    secondaryAction = { saveAndExit() }
                )
            }
        },
        content = {
    Column(){
       state.workout?.id?.let { Text(text= it.toString()) }
        state.workout?.name?.let { Text(text= it) }
        state.workout?.rep?.let { Text(text= it.toString()) }
        state.workout?.weight?.let { Text(text= it.toString()) }
    }
        })

}
