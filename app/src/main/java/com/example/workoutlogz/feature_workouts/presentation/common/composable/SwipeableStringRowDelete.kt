package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableStringRowDelete(
    itemText: String,
    onDelete: () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                onDelete()
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp).padding(6.dp)
                    )
                }
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                       //.padding(10.dp)
                       .background(MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = itemText,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                        .padding(10.dp)
                        //.background(MaterialTheme.colors.primary)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun previewSwipeableStringRowDelete ()
{
    SwipeableStringRowDelete(
        itemText = "item",
        onDelete = {}
    )
    SwipeableRow()
}

@Composable
fun SwipeableRow(
    itemText: String = "Sample Item", // Default parameter for preview
    onDelete: () -> Unit = {} // Default no-op for preview
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = itemText,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
            )
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

//@Composable
//fun ChatCardSection(
//    chatCardItems: List<String>,
//    onSwipeToDelete: (ChatCardItemContent) -> Unit
//) {
//
//
//    LazyColumn(Modifier.fillMaxSize()) {
//
//        items(chatCardItems) {
//            val delete = SwipeAction(
//                onSwipe = {
//                    onSwipeToDelete(it)
//                    Log.d("OnSwipeToDelete", chatCardItems.size.toString())
//                },
//                icon = {
//                    Icon(
//                        Icons.Default.Delete,
//                        contentDescription = "Delete chat",
//                        modifier = Modifier.padding(16.dp),
//                        tint = Color.White
//                    )
//                }, background = Color.Red.copy(alpha = 0.5f),
//                isUndo = true
//            )
//            val archive = SwipeAction(
//                onSwipe = {},
//                icon = {
//                    Icon(
//                        painterResource(id = R.drawable.baseline_archive_24),
//                        contentDescription = "archive chat",
//                        modifier = Modifier.padding(16.dp),
//
//                        tint = Color.White
//
//                    )
//                }, background = Color(0xFF50B384).copy(alpha = 0.7f)
//            )
//            SwipeableActionsBox(
//                modifier = Modifier,
//                swipeThreshold = 200.dp,
//                startActions = listOf(archive),
//                endActions = listOf(delete)
//            ) {
//                ChatCardItem(it)
//            }
//
//        }
//    }
//
//
//}
