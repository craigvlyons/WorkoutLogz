package com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ActionConfirmButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.CancelTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.DialogConfirmButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField

@Composable
fun NewExerciseListScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: AddExerciseListViewModel = hiltViewModel()
) {
    val titleTextField = viewModel.title.value
    val descriptionTextField = viewModel.description.value
    NewExerciseListContent(
        onBack = { viewModel.navBack(openAndPopUp) },
        titleTextField = titleTextField,
        descriptionTextField = descriptionTextField,
        openAndPopUp = openAndPopUp,
        titleValueChange = { newValue ->
            viewModel.onEvent(
                AddExerciseListEvent.EnteredTitle(
                    newValue
                )
            )
        },
        onFocusChange = { newFocusState ->
            viewModel.onEvent(
                AddExerciseListEvent.ChangeTitleFocus(
                    newFocusState
                )
            )
        },
        desValueChange = { newValue ->
            viewModel.onEvent(
                AddExerciseListEvent.EnteredDescription(
                    newValue
                )
            )
        },
        desFocusChange = { newFocusState ->
            viewModel.onEvent(
                AddExerciseListEvent.ChangeDescriptionFocus(
                    newFocusState
                )
            )
        },
        addExerciseList = { viewModel.onEvent(AddExerciseListEvent.AddAddExerciseList) },
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewExerciseListContent(
    onBack: () -> Unit,
    titleTextField: BasicTextFieldState,
    descriptionTextField: BasicTextFieldState,
    openAndPopUp: (String, String) -> Unit,
    titleValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    desValueChange: (String) -> Unit,
    desFocusChange: (FocusState) -> Unit,
    addExerciseList: () -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                title = R.string.AddExerciseListTitle,
                primaryActionIcon = R.drawable.left,
                primaryAction = { onBack() }
            )
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
                    text = titleTextField.text,
                    hint = titleTextField.hint,
                    isHintVisible = titleTextField.isHintVisible,
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

                // Cancel and Done Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CancelTextButton(text = "Cancel") {
                            onBack()
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) // just takes up space to keep things centered.
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        val isEnabled =
                            titleTextField.text.isNotEmpty() && descriptionTextField.text.isNotEmpty()
                        ActionConfirmButton(
                            text = "Add List",
                            action = { addExerciseList() },
                            enable = isEnabled
                        )
                    }
                }
            }
        }
    )
}

