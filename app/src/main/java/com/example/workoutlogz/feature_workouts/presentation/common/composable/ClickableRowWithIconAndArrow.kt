package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList

@Composable
fun ClickableRowWithIconAndArrow(
    iconResourceId: Int, // Resource ID for the leading icon
    title: String, // Text to display
    onClick: () -> Unit, // Click action
    total: String
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon on the left
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null, // Decorative element
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.secondaryVariant // Use original icon color or set a specific color
        )

        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 18.sp
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = total,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 18.sp)

        // Arrow icon on the right
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
fun ClickableRowTitleDateArrow(
    title: String, // Text to display
    onClick: () -> Unit, // Click action
    lastWorkout: String
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 18.sp
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = lastWorkout,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 18.sp)

        // Arrow icon on the right
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
fun ClickableRowIconTitle(
    iconResourceId: Int, // Resource ID for the leading icon
    title: String, // Text to display
    onClick: () -> Unit, // Click action
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon on the left
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null, // Decorative element
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.secondaryVariant // Use original icon color or set a specific color
        )

        // Title
        Text(
            text = title,
            style = textStyle,
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 18.sp
        )

        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun ClickableRowIconExerciseArrow(
    iconResourceId: Int, // Resource ID for the leading icon
    exercise: ExerciseList, // Text to display
    onClick: () -> Unit, // Click action
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon on the left
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null, // Decorative element
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.secondaryVariant // Use original icon color or set a specific color
        )
        Column {

        // Title
            Text(
                text = exercise.name,
                style = textStyle,
                modifier = Modifier.padding(start = 10.dp),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 18.sp
            )
            // Title
            Text(
                text = exercise.description,
                style = textStyle,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 16.sp
            )

        }

        Spacer(Modifier.weight(1f))
        // Arrow icon on the right
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

