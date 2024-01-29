package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableTextBox(item: String, onDismiss: (String) -> Unit) {
    val dismissState = rememberDismissState(
        confirmStateChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart) {
                onDismiss(item) // Trigger the event to delete the item
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        background = { /* Background content if needed, like a delete icon */ },
        dismissContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BasicText(
                    text = item,
                    style = MaterialTheme.typography.subtitle1
                ) // Your text here
            }
        }
    )

    // Optional: Automatically reset the dismiss state to default after action
    val scope = rememberCoroutineScope()
    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue != DismissValue.Default) {
            scope.launch {
                dismissState.reset()
            }
        }
    }
}