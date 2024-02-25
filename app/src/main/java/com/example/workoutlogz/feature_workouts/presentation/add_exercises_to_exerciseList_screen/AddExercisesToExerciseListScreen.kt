package com.example.workoutlogz.feature_workouts.presentation.add_exercises_to_exerciseList_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListViewModel


@Composable
fun AddExercisesToExerciseListScreen(
    exercises: List<String>,
    selectedExercises: Set<String>,
    onSearchQueryChanged: (String) -> Unit,
    onExerciseSelected: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    AddExercisesToExerciseListContent(
        exercises,
        selectedExercises,
        onSearchQueryChanged,
        onExerciseSelected,
        onSearch
    )
}

@Composable
fun AddExercisesToExerciseListContent(
    exercises: List<String>,
    selectedExercises: Set<String>,
    onSearchQueryChanged: (String) -> Unit,
    onExerciseSelected: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column {
        SearchBar(
            query = searchQuery,
            onQueryChanged = { query ->
                searchQuery = query
                onSearchQueryChanged(query)
            },
            onSearch = onSearch
        )
        LazyColumn {
            items(exercises) { exercise ->
                ExerciseItem(
                    exerciseName = exercise,
                    isSelected = selectedExercises.contains(exercise),
                    onSelectExercise = onExerciseSelected
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text("Search or enter exercise name...") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun ExerciseItem(
    exerciseName: String,
    isSelected: Boolean,
    onSelectExercise: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectExercise(exerciseName) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource( if (isSelected) R.drawable.circle_check else R.drawable.plus),
            contentDescription = if (isSelected) "Selected" else "Not Selected",
            modifier = Modifier
                .size(24.dp),
            tint = MaterialTheme.colors.secondaryVariant
        )       
        Text(
            text = exerciseName,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(start = 20.dp)
        )
    }
}


