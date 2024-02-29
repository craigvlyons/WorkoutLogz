package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.add_exercises_to_exerciseList_screen.AddExercisesToExerciseListScreen
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_DynamicTitle


@Composable
fun WorkoutScreen(
    openAndPopUp: (String, String) -> Unit,
    exerciseListId: Int = -1,
    workoutName: String = "",
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    WorkoutContent(
        state = state
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun WorkoutContent(
    state: WorkoutState
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope() // We need to remember the coroutine scope
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    // Main content
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            // composable pop up from bottom to add new workout...

        }
    ) {
        Scaffold(
            topBar = {
                TopToolbar_DynamicTitle(
                    modifier = Modifier,
                    primaryActionIcon = R.drawable.left,
                    title = "",
                    primaryAction = { },
                    secondaryActionIcon = R.drawable.edit,
                    secondaryAction = { }
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            content = {
                Column(
                    modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondaryVariant)) {
                    Text(
                        text = "Workout Screen",
                        color = Color.Magenta
                    )
                }
            }
        )
    }
}