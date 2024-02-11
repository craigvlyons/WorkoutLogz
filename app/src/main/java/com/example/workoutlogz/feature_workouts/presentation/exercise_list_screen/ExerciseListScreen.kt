package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseNameList
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun ExerciseListScreen(
    popUpScreen: () -> Unit,
    exerciseListId: Int = -1,
    viewModel: ExerciseListViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExerciseListContent(
        state

    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExerciseListContent(
    state: ExerciseListState

){

    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.ic_menu,
                title = R.string.ExerciseListTitle,
                primaryAction = { /* */ },
                secondaryActionIcon = null,
                secondaryAction = { /* Handle secondary action here */ }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        content = {

            Column {



                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .size(1.dp))

                state.exerciseList?.let { it1 -> Text(text = it1.name) }

            }
        }
    )
}

