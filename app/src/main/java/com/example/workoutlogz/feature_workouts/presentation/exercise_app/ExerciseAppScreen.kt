package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.NEW_EXERCISE_LIST_SCREEN
import com.example.workoutlogz.feature_workouts.data.models.Exercise
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowIconExerciseArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowIconTitle
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ClickableRowWithIconAndArrow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SwipeableRow
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SwipeableStringRowDelete
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SwipeableTextBox
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.ui.theme.Shapes
import java.time.format.TextStyle


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
        onSettingsClick =  {viewModel.onSettingsClick(openScreen)} ,
        onExerciseClick =  {viewModel.onExerciseClick(openScreen)} ,
        openScreen = openScreen,
        exerciseNamesList = exerciseNamesList,
        exerciseList = exerciseList,
        deleteId = { id -> viewModel.onEvent(ExerciseEvent.DeleteById(id)) },
        onExerciseListClick = {openScreen, id ->  viewModel.onExerciseListClick(openScreen, id) }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciseScreenContent(
    modifier: Modifier = Modifier,
    onSettingsClick: ((String ) -> Unit) -> Unit ,
    onExerciseClick: ((String ) -> Unit) -> Unit ,
    openScreen : (String) -> Unit,
    exerciseNamesList: List<Exercise>,
    exerciseList: List<ExerciseList>,
    deleteId : (Int) -> Unit,
    onExerciseListClick: ((String) -> Unit, Int) -> Unit
){
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.ic_menu,
                title = R.string.ExerciseTitle,
                primaryAction = {  onSettingsClick(openScreen)},
                secondaryActionIcon = R.drawable.ic_menu,
                secondaryAction = { onExerciseClick(openScreen) }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier
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
//            ExerciseNameList(
//                title = "Exercises",
//                exerciseNameList = exerciseNamesList ,
//                deleteId = (deleteId)
//            )
        }
    }
}

@Composable
fun SummarySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text("Summary", style = MaterialTheme.typography.h6)
        Divider(color = Color.Gray, thickness = 1.dp)
        Row(modifier = Modifier.padding(top = 8.dp)) {
            SummaryItem("Sets", 29)
            Spacer(modifier = Modifier.width(8.dp))
            SummaryItem("Repetitions", 237)
        }
        // Add more summary items if needed
    }
}

@Composable
fun ExerciseNameList(
    title: String,
    exerciseNameList : List<Exercise>,
    deleteId: (Int) -> Unit
    ){
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(Shapes.large)
        .background(MaterialTheme.colors.primary)
        .padding(10.dp)

    ) {
    Text(modifier = Modifier.padding(6.dp),
        text = title,
        style = MaterialTheme.typography.h1
        )
        // SwipeableStringRowDelete works
        // SwipeableStringRowDelete(itemText = "string row delete") {  }
        // SwipeableRow()
    Divider(color = MaterialTheme.colors.secondary, thickness = 1.dp)
    LazyColumn() {
            items (items = exerciseNameList,
                key = { exercise ->
                    exercise.id
                }
                ) { ex ->
                SwipeableTextBox(item = ex.name, onDismiss = { deleteId(ex.id)  })
            }
    }
    }
}
@Composable
fun ExerciseListMainPage(
    exerciseLists : List<ExerciseList>,
    openScreen: (String) -> Unit,
    onExerciseClick:((String) -> Unit) -> Unit,
    onExerciseListClick: ((String) -> Unit , Int) -> Unit
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
            iconResourceId = R.drawable.ic_menu,
            title = "New List...",
            onClick = { openScreen(NEW_EXERCISE_LIST_SCREEN) },
            textStyle = MaterialTheme.typography.subtitle1
        )
        ClickableRowWithIconAndArrow(
            iconResourceId = R.drawable.ic_menu,
            title = "My Exercises",
            total = "18",
            onClick = { onExerciseClick(openScreen) }
        )
        LazyColumn() {
            items(
                items = exerciseLists,
                key = { exerciseList ->
                    exerciseList.id
                }
            ) { exerciseList ->
                ClickableRowIconExerciseArrow(
                    iconResourceId = R.drawable.ic_menu,
                    exercise = exerciseList,
                    onClick = {  onExerciseListClick(openScreen, exerciseList.id) },
                    textStyle = MaterialTheme.typography.subtitle1
                )
            }
        }


//        ClickableRowIconExerciseArrow(
//            iconResourceId = R.drawable.ic_menu,
//            exercise = newexercise,
//            onClick = { },
//            textStyle = MaterialTheme.typography.subtitle1
//        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .clickable { /* TODO: Handle New List click */ }
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Add List",
//                tint = MaterialTheme.colors.secondaryVariant,
//                modifier = Modifier
//                    .size(24.dp)
//            )
//
//            Text(
//                text = "New List...",
//                style = MaterialTheme.typography.body1,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .clickable { /* TODO: Handle My Exercises click */ }
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_menu),
//                contentDescription = "My Exercises",
//                modifier = Modifier.size(24.dp),
//                tint = MaterialTheme.colors.secondaryVariant
//            )
//
//            Text(
//                text = "My Exercises - total >",
//                style = MaterialTheme.typography.body1,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        LazyColumn(modifier = Modifier.padding(16.dp)) {
//            /* TODO: Add exercise lists here */
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .clickable { /* TODO: Handle title click */ }
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_menu),
//                contentDescription = "Title",
//                modifier = Modifier.size(24.dp),
//                tint = MaterialTheme.colors.secondaryVariant
//            )
//            Column(modifier = Modifier.padding(start = 8.dp)) {
//                Text(
//                    text = "Title",
//                    style = MaterialTheme.typography.body1
//                )
//                Text(
//                    text = "Description",
//                    style = MaterialTheme.typography.caption,
//                    color = Color.Gray
//                )
//            }
//            Text(
//                text = "total >",
//                style = MaterialTheme.typography.body1,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//    }
    }
}


    @Composable
    fun SummaryItem(title: String, count: Int) {
        /* TODO set up summery on home page.
        layout date in grey
        Summery title in white, text "SUMMERY"
        2 rows and 2 columns , with sets, reps, exercises, and volume.
     */
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count.toString(), style = MaterialTheme.typography.h5)
            Text(text = title, style = MaterialTheme.typography.subtitle1)
        }
    }


//@Composable
//fun ExerciseListSection() {
//    // You can use a LazyColumn for scrolling if you have many items
//    Column {
//        ExerciseListItem("Day 1", "Full body workouts for day 1.")
//        ExerciseListItem("Day 2", "Workouts for day 2")
//        ExerciseListItem("Day 3", "Workouts for day 3")
//        // Add more items or use a loop to generate items
//    }
//}

//@Composable
//fun ExerciseListItem(day: String, description: String) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(16.dp)) {
//        Text(day, style = MaterialTheme.typography.h6)
//        Text(description, style = MaterialTheme.typography.body1)
//        Divider(color = Color.Gray, thickness = 1.dp)
//    }
//}


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


