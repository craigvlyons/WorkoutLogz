package com.example.workoutlogz.feature_workouts.presentation.edit_exercise_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.add_exercises_to_exerciseList_screen.ExerciseItem
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ActionConfirmButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.CancelTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_TextButtonTitleTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListState
import com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen.BasicTextFieldState


@Composable
fun EditExerciseListScreen(
    openAndPopUp: (String, String) -> Unit,
    exerciseListId: Int = -1,
    viewModel: EditExerciseListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val nameTextField = viewModel.exerciseListName.value
    val descriptionTextField = viewModel.description.value
    val exercises = viewModel.allExerciseNames.value
    val selectedExercises = viewModel.selectedExercises.value
    EditExerciseListContent(
        onBack = { viewModel.navBack(openAndPopUp) },
        state = state,
        nameTextField = nameTextField,
        descriptionTextField = descriptionTextField,
        openAndPopUp = openAndPopUp,
        titleValueChange = { newValue ->
            viewModel.onEvent(
                EditExerciseListEvent.EnteredTitle(
                    newValue
                )
            )
        },
        onFocusChange = { newFocusState ->
            viewModel.onEvent(
                EditExerciseListEvent.ChangeTitleFocus(
                    newFocusState
                )
            )
        },
        desValueChange = { newValue ->
            viewModel.onEvent(
                EditExerciseListEvent.EnteredDescription(
                    newValue
                )
            )
        },
        desFocusChange = { newFocusState ->
            viewModel.onEvent(
                EditExerciseListEvent.ChangeDescriptionFocus(
                    newFocusState
                )
            )
        },
        saveAndExit = {
            viewModel.onEvent(EditExerciseListEvent.SaveExerciseList)
            viewModel.navBack(openAndPopUp)
        },
        exercises = exercises,
        selectedExercises = selectedExercises,
        onExerciseSelected = { name -> viewModel.onEvent(EditExerciseListEvent.ToggleExerciseSelection(name)) }
    )
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditExerciseListContent(
    onBack: () -> Unit,
    state: ExerciseListState,
    nameTextField: BasicTextFieldState,
    descriptionTextField: BasicTextFieldState,
    exercises: List<String>,
    selectedExercises: Set<String>,
    openAndPopUp: (String, String) -> Unit,
    titleValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    desValueChange: (String) -> Unit,
    desFocusChange: (FocusState) -> Unit,
    onImeAction: () -> Unit = {},
    saveAndExit: () -> Unit,
    onExerciseSelected: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            state.exerciseList?.let {
                TopToolbar_TextButtonTitleTextButton(
                    modifier = Modifier,
                    title = it.name,
                    primaryAction = { onBack() },
                    secondaryAction = { saveAndExit() }
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.clipboard
                    ),
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                // name label
                Text(
                    text = "NAME",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 5.dp)
                        .align(Alignment.Start)
                )
                //  Name Input
                TransparentHintField(
                    text = nameTextField.text,
                    hint = nameTextField.hint,
                    isHintVisible = nameTextField.isHintVisible,
                    onValueChange = titleValueChange,
                    textStyle = MaterialTheme.typography.h3,
                    onFocusChange = onFocusChange
                )
                // text exercise name examples
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 5.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Organized by ",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Text(
                        text = "workout, muscle group, day of the week, etc...",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))

                // label description
                Text(
                    text = "DESCRIPTION",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 5.dp)
                        .align(Alignment.Start)
                )
                // Description Input
                TransparentHintField(
                    text = descriptionTextField.text,
                    hint = descriptionTextField.hint,
                    isHintVisible = descriptionTextField.isHintVisible,
                    onValueChange = desValueChange,
                    textStyle = MaterialTheme.typography.h3,
                    onFocusChange = desFocusChange
                )
                Spacer(modifier = Modifier.height(16.dp))
                // add list of exercises...
                LazyColumn {
                    items(exercises) { exercise ->
                        ExerciseItemSelectable(
                            exerciseName = exercise,
                            isSelected = selectedExercises.contains(exercise),
                            onSelectExercise = onExerciseSelected
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ExerciseItemSelectable(
    exerciseName: String,
    isSelected: Boolean,
    onSelectExercise: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectExercise(exerciseName) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource( if (isSelected) R.drawable.circle_check else R.drawable.circle),
            contentDescription = if (isSelected) "Selected" else "Not Selected",
            modifier = Modifier
                .size(24.dp),
            tint = if(isSelected) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
        )
        Text(
            text = exerciseName,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(start = 20.dp)
        )
    }
}
