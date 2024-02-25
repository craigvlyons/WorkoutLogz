package com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.add_exercises_to_exerciseList_screen.AddExercisesToExerciseListScreen
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ActionIconTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowTitleDateArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowWithIconAndArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_DynamicTitle
import com.example.workoutlogz.ui.theme.Shapes
import kotlinx.coroutines.launch


@Composable
fun ExerciseListScreen(
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit,
    exerciseListId: Int = -1,
    viewModel: ExerciseListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val title by viewModel.title
    val allExerciseNames by viewModel.allExerciseNames
    val selectedExercises by viewModel.selectedExercises
    ExerciseListContent(
        onBack = { viewModel.navBack(openAndPopUp) },
        openScreen =  openScreen,
        state = state,
        title =  title,
        allExerciseNames = allExerciseNames,
        selectedExercises = selectedExercises,
        onExerciseSelected = { name ->
            viewModel.onEvent(
                ExerciseListEvent.ToggleExerciseSelection(
                    name
                )
            )
        },
        onExerciseListClick = { openScreen, id -> viewModel.onEditExerciseListClick(openScreen, id) }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ExerciseListContent(
    onBack: () -> Unit,
    openScreen: (String) -> Unit,
    state: ExerciseListState,
    title: String,
    allExerciseNames: List<String>,
    selectedExercises: Set<String>,
    onExerciseSelected: (String) -> Unit,
    onExerciseListClick: ((String) -> Unit, Int) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope() // We need to remember the coroutine scope
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


    // Main content
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            AddExercisesToExerciseListScreen(
                exercises = allExerciseNames, //viewModel.exerciseNames, // Assuming this is a list in your ViewModel
                selectedExercises = selectedExercises, // viewModel.selectedExercises, // Assuming this is a set in your ViewModel
                onSearchQueryChanged = { }, // viewModel::onSearchQueryChanged, // Example function in your ViewModel
                onExerciseSelected = onExerciseSelected, // viewModel::onExerciseSelected, // Example function in your ViewModel
                onSearch = { } // viewModel::onSearch // Example function in your ViewModel
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopToolbar_DynamicTitle(
                    modifier = Modifier,
                    primaryActionIcon = R.drawable.left,
                    title = title,
                    primaryAction = { onBack() },
                    secondaryActionIcon = R.drawable.edit,
                    secondaryAction = { state.exerciseList?.let { onExerciseListClick(openScreen, it.id) } }
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    state.exerciseList?.let { it1 -> Text(text = it1.description) }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp)
                    )

                    // Lazy Column
                    LazyColumn(
                        modifier = Modifier
                        .clip(shape = Shapes.large)
                        .background(MaterialTheme.colors.primary)) {
                        items(
                            items = state.exerciseNamesList,
                            key = { it }
                        ) {
                            ClickableRowTitleDateArrow(
                                title = it,
                                onClick = { /*TODO*/ },
                                lastWorkout = "Yesterday"
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f)) // Pushes the button to the bottom
                    ActionIconTextButton(
                        onClick = {
                        coroutineScope.launch {
                        if (sheetState.isVisible) {
                            sheetState.hide()
                        } else {
                            sheetState.show()
                        }
                    } },
                        icon = R.drawable.plus, title = "Add Exercise"
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        )
    }
}

