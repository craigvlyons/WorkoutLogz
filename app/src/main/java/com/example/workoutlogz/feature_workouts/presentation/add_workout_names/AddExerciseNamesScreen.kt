package com.example.workoutlogz.feature_workouts.presentation.add_workout_names

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseNameList
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.format.TextStyle


@Composable
fun AddExerciseNamesScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: AddExerciseNamesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val nameTextField = viewModel.nameTextField.value
    AddExerciseNamesScreenContent(
        onBack = {viewModel.navBack(openAndPopUp)},
        nameTextField = nameTextField,
        onValueChange = { newValue -> viewModel.onEvent(AddNewExerciseName.EnteredSearch(newValue))  },
        onFocusChange = { newFocusState -> viewModel.onEvent(AddNewExerciseName.ChangeSearchFocus(newFocusState)) },
        saveExercise = { viewModel.onEvent(AddNewExerciseName.AddExercise)},
        state = state,
        deleteId = { deleteId -> viewModel.onEvent(AddNewExerciseName.DeleteExerciseById(deleteId))},
    )
}



@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddExerciseNamesScreenContent(
    onBack: () -> Unit,
    nameTextField: AddExerciseTextFieldState,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    saveExercise: () -> Unit,
    state: AddExerciseNamesState,
    deleteId: (Int) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.ic_menu,
                title = R.string.SettingsTitle,
                primaryAction = { onBack() },
                secondaryActionIcon = null,
                secondaryAction = { /* Handle secondary action here */ }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        content = {

            Column {
                TransparentHintField(
                    text = nameTextField.text,
                    hint = nameTextField.hint,
                    onValueChange = onValueChange,
                    onFocusChange =  onFocusChange,
                    isHintVisible = nameTextField.isHintVisible,
                    textStyle = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(10.dp)
                        .onKeyEvent { keyEvent ->
                    if (keyEvent.key == Key.Enter && keyEvent.type == KeyEventType.KeyUp){
                        saveExercise()
                        keyboardController?.hide()
                        true
                    }else false
                }
                )

                Button(onClick = { saveExercise() }) {
                    Text("Add Exercise")
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(1.dp))

                ExerciseNameList(
                    title = "Exercises",
                    exerciseNameList = state.exerciseList,
                    deleteId = (deleteId)
                    )
            }
        }
    )
}


