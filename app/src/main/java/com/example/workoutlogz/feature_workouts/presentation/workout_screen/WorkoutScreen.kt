package com.example.workoutlogz.feature_workouts.presentation.workout_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.WORKOUT_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.presentation.NewWorkoutScreen
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_DynamicTitle
import com.example.workoutlogz.ui.theme.Shapes
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter


@SuppressLint("NewApi", "SuspiciousIndentation")
@Composable
fun WorkoutScreen(
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit,
    exerciseListId: Int = -1,
    workoutName: String = "",
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val workoutName = viewModel.workoutName
    val lastWorkout by viewModel.lastWorkout.collectAsStateWithLifecycle()

        WorkoutContent(
            state = state,
            onBack = { viewModel.navBack(openAndPopUp) },
            openScreen = openScreen,
            workoutName = workoutName,
            lastWorkout = lastWorkout,
            onRepChange = { rep -> viewModel.onEvent(WorkoutEvent.EnteredReps(rep)) },
            onWeightChange = { weight -> viewModel.onEvent(WorkoutEvent.EnteredWeight(weight)) },
            saveWorkout = { viewModel.onEvent(WorkoutEvent.SaveWorkout) },
            editWorkout = {openScreen, id -> viewModel.editWorkoutScreen( openScreen, id) }
        )


}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun WorkoutContent(
    state: WorkoutState,
    onBack: () -> Unit,
    openScreen: (String) -> Unit,
    workoutName: String,
    lastWorkout: Workout,
    onRepChange: (Int) -> Unit,
    onWeightChange: (Double) -> Unit,
    saveWorkout: () -> Unit,
    editWorkout:  ((String) -> Unit, Int) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope() // We need to remember the coroutine scope
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    // Main content
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            // composable pop up from bottom to add new workout...
            NewWorkoutScreen(
                lastWorkout = lastWorkout,
                onRepChange = onRepChange,
                onWeightChange = onWeightChange,
                submitWorkout = saveWorkout
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopToolbar_DynamicTitle(
                    modifier = Modifier,
                    primaryActionIcon = R.drawable.left,
                    title = workoutName,
                    primaryAction = { onBack() },
                    secondaryActionIcon = R.drawable.edit,
                    secondaryAction = { }
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) {
                                sheetState.hide()
                            } else {
                                sheetState.show()
                            }
                        }
                    },
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                )
                {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "record new set",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //TODO comparison to previous workout...

                    // TODO Workout lists, separated by day.
                    LazyColumn(
                        modifier = Modifier
                            .clip(shape = Shapes.large)
                            .background(MaterialTheme.colors.primary)
                    ) {
                        items(
                            items = state.workouts,
                            key = { workout -> workout.id }
                        ) {
                            workoutRow(workout = it, onClick = { editWorkout( openScreen, it.id)})

                        }
                    }
                }
            }
        )
    }
}

// Workout composable to display time then space rep in green - sets in orange - right arrow
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun workoutRow(
    workout: Workout,
    onClick: () -> Unit
) {
    // Define a formatter
    val formatter = DateTimeFormatter.ofPattern("h:mm a") // Example: 12:08 PM

    // Format the LocalDateTime color grey.
    val formattedTime = workout.date.format(formatter)
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 18.sp,
            color = MaterialTheme.colors.secondary
        )

        Spacer(Modifier.weight(1f))
        // create a string variable and format the rep string. rep color Green / lb color orange

        repText(workout.rep)
        lbText(workout.weight)

        // Arrow icon on the right, color grey
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Go", // Accessibility description
            modifier = Modifier
                .size(30.dp),
            //.padding(start = 8.dp),
            tint = MaterialTheme.colors.secondary // Set the color of the arrow icon
        )
    }
}

@Composable
fun repText(rep: Int){
    Row(verticalAlignment = Alignment.Bottom){
        Text(
            text = "$rep",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 20.sp,
            color = com.example.workoutlogz.ui.theme.LightGreen
        )
        Text(
            text = "rep",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = .5.dp),
            fontSize = 16.sp,
            color = com.example.workoutlogz.ui.theme.LightGreen
        )
    }
}

@Composable
fun lbText(weight: Double){
    Row(verticalAlignment = Alignment.Bottom){
        Text(
            text = "$weight",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 20.sp,
            color = com.example.workoutlogz.ui.theme.GoldenOrange
        )
        Text(
            text = "lb",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = .5.dp),
            fontSize = 16.sp,
            color = com.example.workoutlogz.ui.theme.GoldenOrange
        )
    }
}