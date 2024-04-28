package com.example.workoutlogz.feature_workouts.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.presentation.common.composable.BasicButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SmallBasicTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.SmallOutlineButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import com.example.workoutlogz.ui.theme.Shapes

@Composable
fun NewWorkoutScreen(
    lastWorkout: Workout,
    onRepChange: (Int) -> Unit,
    onWeightChange: (Double) -> Unit,
    submitWorkout: () -> Unit
) {
    NewWorkoutScreenContent(
        lastWorkout = lastWorkout,
        onRepChange = onRepChange,
        onWeightChange = onWeightChange,
        submitWorkout = submitWorkout
    )
}

@Composable
fun NewWorkoutScreenContent(
    lastWorkout: Workout,
    onRepChange: (Int) -> Unit,
    onWeightChange: (Double) -> Unit,
    submitWorkout: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = "REPETITIONS & WEIGHT",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        workoutRep(rep = lastWorkout.rep, onRepChange = onRepChange)
        Spacer(modifier = Modifier.height(8.dp))
        workoutWeight(weight = lastWorkout.weight, onWeightChange = onWeightChange)
        Spacer(modifier = Modifier.height(16.dp))
        TransparentHintField(
            text = "",
            hint = "note",
            onFocusChange = {},
            onValueChange = {},
            isHintVisible = true,
            textStyle = MaterialTheme.typography.body1
        )
        BasicButton(
            text = "Record Workout",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            action = { submitWorkout() })
    }
}

@Composable
fun workoutWeight(
    weight: Double,
    onWeightChange: (Double) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clip(shape = Shapes.medium)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.minus),
            contentDescription = "Minus",
            modifier = Modifier
                .size(16.dp)
                .padding(start = 8.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        SmallBasicTextButton(text = "10", action = { onWeightChange(-10.0) })
        SmallBasicTextButton(text = "2.5", action = { onWeightChange(-2.5) })
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "$weight lb", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.weight(1f))
        SmallBasicTextButton(text = "2.5", action = { onWeightChange(2.5) })
        SmallBasicTextButton(text = "10", action = { onWeightChange(10.0) })
        Icon(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "Plus",
            modifier = Modifier
                .size(16.dp)
                .padding(end = 8.dp),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun workoutRep(
    rep: Int,
    onRepChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clip(shape = Shapes.medium)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.minus),
            contentDescription = "Minus",
            modifier = Modifier
                .size(16.dp)
                .padding(start = 8.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        SmallBasicTextButton(text = "1", action = { onRepChange(-1) })
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "$rep rep", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.weight(1f))
        SmallBasicTextButton(text = "1", action = { onRepChange(1) })
        Icon(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "Plus",
            modifier = Modifier
                .size(16.dp)
                .padding(end = 8.dp),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}