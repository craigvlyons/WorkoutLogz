package com.example.workoutlogz.feature_workouts.presentation.exercise_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.common.composable.ActionToolbar
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExerciseAppScreen(
    openScreen: (String) -> Unit,
    viewModel: ExerciseAppViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.ic_menu,
                title = R.string.ExerciseTitle,
                primaryAction = { /* */ },
                secondaryActionIcon = R.drawable.ic_menu,
                secondaryAction = { /* Handle secondary action here */ }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SummarySection()
            ExerciseListSection()
            StartSessionButton()
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
fun SummaryItem(title: String, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count.toString(), style = MaterialTheme.typography.h5)
        Text(text = title, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ExerciseListSection() {
    // You can use a LazyColumn for scrolling if you have many items
    Column {
        ExerciseListItem("Day 1", "Full body workouts for day 1.")
        ExerciseListItem("Day 2", "Workouts for day 2")
        ExerciseListItem("Day 3", "Workouts for day 3")
        // Add more items or use a loop to generate items
    }
}

@Composable
fun ExerciseListItem(day: String, description: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(day, style = MaterialTheme.typography.h6)
        Text(description, style = MaterialTheme.typography.body1)
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}


@Composable
fun StartSessionButton() {
    Button(
        onClick = { /* TODO: Handle click */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
    ) {
        Text("New Session", color = Color.White)
    }
}


