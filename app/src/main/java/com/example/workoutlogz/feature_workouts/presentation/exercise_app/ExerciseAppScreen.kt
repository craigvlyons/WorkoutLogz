package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.NEW_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowIconExerciseArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowIconTitle
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowWithIconAndArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SwipeableTextBox
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_TitleIcon
import com.example.workoutlogz.ui.theme.Shapes


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciseAppScreen(
    openScreen: (String) -> Unit,
    viewModel: ExerciseAppViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val exerciseNamesList = state.exercises
    val exerciseList = state.exerciseList
    ExerciseScreenContent(
        onSettingsClick = { viewModel.onSettingsClick(openScreen) },
        onExerciseClick = { viewModel.onExerciseClick(openScreen) },
        openScreen = openScreen,
        exerciseNamesList = exerciseNamesList,
        exerciseList = exerciseList,
        deleteId = { id -> viewModel.onEvent(ExerciseEvent.DeleteById(id)) },
        onExerciseListClick = { openScreen, id -> viewModel.onExerciseListClick(openScreen, id) }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciseScreenContent(
    modifier: Modifier = Modifier,
    onSettingsClick: ((String) -> Unit) -> Unit,
    onExerciseClick: ((String) -> Unit) -> Unit,
    openScreen: (String) -> Unit,
    exerciseNamesList: List<Exercise>,
    exerciseList: List<ExerciseList>,
    deleteId: (Int) -> Unit,
    onExerciseListClick: ((String) -> Unit, Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopToolbar_TitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.settings,
                title = R.string.ExerciseTitle,
                primaryAction = { onSettingsClick(openScreen) },
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SummarySection()
            ExerciseListMainPage(
                exerciseList,
                openScreen,
                onExerciseClick,
                onExerciseListClick
            )
        }
    }
}

@Composable
fun SummarySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text("Summary", style = MaterialTheme.typography.h1)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(Shapes.large)
                .background(MaterialTheme.colors.primary),
            //horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier.weight(1f).padding(6.dp), contentAlignment = Alignment.Center) {
                Column {
                    SummaryItem("Sets", 29)
                    Spacer(modifier = Modifier)
                    SummaryItem("Repetitions", 237)
                }
            }
            Box(modifier = Modifier.weight(1f)) {

            }
            Box(modifier = Modifier.weight(1f).padding(6.dp), contentAlignment = Alignment.Center) {
                Column {
                    SummaryItem("Exercises", 9)
                    Spacer(modifier = Modifier)
                    SummaryItem("Volume", 4789)
                }
            }


            // TODO: button to go to summery screen
        }
    }
}


@Composable
fun ExerciseNameList(
    title: String,
    exerciseNameList: List<Exercise>,
    deleteId: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(6.dp)
                .padding(start = 12.dp),
            text = title,
            style = MaterialTheme.typography.h2
        )
        LazyColumn(
            modifier = Modifier
                .clip(Shapes.large)
                .background(MaterialTheme.colors.primary)
        ) {
            items(items = exerciseNameList,
                key = { exercise ->
                    exercise.id
                }
            ) { ex ->
                SwipeableTextBox(item = ex.name, onDismiss = { deleteId(ex.id) })
            }
        }
    }
}

@Composable
fun ExerciseListMainPage(
    exerciseLists: List<ExerciseList>,
    openScreen: (String) -> Unit,
    onExerciseClick: ((String) -> Unit) -> Unit,
    onExerciseListClick: ((String) -> Unit, Int) -> Unit
) {
    Text(
        text = "Exercise Lists",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(16.dp),

        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shapes.large)
            .background(MaterialTheme.colors.primary)
            .padding(6.dp)
    ) {

        ClickableRowIconTitle(
            iconResourceId = R.drawable.clipboard,
            title = "New List...",
            onClick = { openScreen(NEW_EXERCISE_LIST_SCREEN) },
            textStyle = MaterialTheme.typography.subtitle1
        )
        ClickableRowWithIconAndArrow(
            iconResourceId = R.drawable.dumbbell_solid,
            title = "My Exercises",
            total = "18",
            onClick = { onExerciseClick(openScreen) }
        )
        LazyColumn {
            items(
                items = exerciseLists,
                key = { exerciseList ->
                    exerciseList.id
                }
            ) { exerciseList ->
                ClickableRowIconExerciseArrow(
                    iconResourceId = R.drawable.clipboard,
                    exercise = exerciseList,
                    onClick = { onExerciseListClick(openScreen, exerciseList.id) },
                    textStyle = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}


@Composable
fun SummaryItem(title: String, count: Int) {
    /* TODO set up summery on home page.
    layout date in grey
    Summery title in white, text "SUMMERY"
    2 rows and 2 columns , with sets, reps, exercises, and volume.
 */
    Column(
        modifier = Modifier.padding(6.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.subtitle1)
        Text(text = count.toString(), style = MaterialTheme.typography.h5)
    }
}


//@Composable
//fun StartSessionButton() {
//    Button(
//        onClick = { /* TODO: Handle click */ },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
//    ) {
//        Text("New Session", color = Color.White)
//    }
//}


