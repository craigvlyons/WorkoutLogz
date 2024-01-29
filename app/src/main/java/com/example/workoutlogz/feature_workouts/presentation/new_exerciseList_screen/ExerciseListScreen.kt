package com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField

@Composable
    fun NewExerciseListScreen(
        openAndPopUp: (String, String) -> Unit,
        viewModel: ExerciseListViewModel = hiltViewModel()
        ){
    val titleTextField = viewModel.title.value
    val descriptionTextField = viewModel.description.value
    NewExerciseListContent(
        onBack = { viewModel.navBack(openAndPopUp) },
        titleTextField = titleTextField,
        descriptionTextField = descriptionTextField,
        openAndPopUp = openAndPopUp,
        titleValueChange = {newValue -> viewModel.onEvent(ExerciseListEvent.EnteredTitle(newValue))  },
        onFocusChange = {newFocusState -> viewModel.onEvent(ExerciseListEvent.ChangeTitleFocus(newFocusState))   },
        desValueChange = {newValue -> viewModel.onEvent(ExerciseListEvent.EnteredDescription(newValue)) },
        desFocusChange = {newFocusState -> viewModel.onEvent(ExerciseListEvent.ChangeDescriptionFocus(newFocusState)) },
        addExerciseList = { viewModel.onEvent(ExerciseListEvent.AddExerciseList)},
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
    titleValueChange : (String) -> Unit,
    onFocusChange : (FocusState) -> Unit ,
    desValueChange : (String) -> Unit,
    desFocusChange : (FocusState) -> Unit ,
    addExerciseList: () -> Unit,
    ){
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
            modifier = Modifier,
            title = R.string.AddExerciseListTitle,
            primaryActionIcon = R.drawable.ic_menu,
            primaryAction = { onBack() }
            )
                 },

    content = {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Create New Exercise List",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //  Name Input
        TransparentHintField(
            text = titleTextField.text,
            hint = titleTextField.hint,
            isHintVisible = titleTextField.isHintVisible,
            onValueChange= titleValueChange,
            textStyle = MaterialTheme.typography.subtitle1,
            onFocusChange = onFocusChange
        )

        // Description Input
        TransparentHintField(
            text = descriptionTextField.text,
            hint = descriptionTextField.hint,
            isHintVisible = descriptionTextField.isHintVisible,
            onValueChange = desValueChange,
            textStyle = MaterialTheme.typography.subtitle1,
            onFocusChange = desFocusChange
        )

        // Cancel and Done Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {onBack()},
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    if (titleTextField.text.isNotEmpty() && descriptionTextField.text.isNotEmpty()) {
                        addExerciseList()
                    }
                }
            ) {
                if (titleTextField.text.isNotEmpty() && descriptionTextField.text.isNotEmpty()){
                    Text(
                        text = "Done",
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }else
                {
                    Text(
                        text = "Done",
                        color = MaterialTheme.colors.secondary
                        )
                }
            }
        }
    }

}
    )
}