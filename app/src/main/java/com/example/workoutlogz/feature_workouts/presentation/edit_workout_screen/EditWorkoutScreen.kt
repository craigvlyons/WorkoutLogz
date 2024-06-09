package com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.presentation.common.composable.BasicField
import com.example.workoutlogz.feature_workouts.presentation.common.composable.CommentHintField
import com.example.workoutlogz.feature_workouts.presentation.common.composable.DeleteIconButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_TextButtonTitleTextButton
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import com.example.workoutlogz.feature_workouts.presentation.common.composable.WorkoutDatePicker
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListViewModel
import com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen.BasicTextFieldState
import java.time.LocalDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditWorkoutScreen(
    openAndPopUp: (String, String) -> Unit,
    workoutId: Int = -1,
    exerciseListId: Int = -1,
    viewModel: EditWorkoutViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val reps = viewModel.repsText.value
    val weight = viewModel.weightText.value
    val note = viewModel.notesText.value

        EditWorkoutContent(
            state = state,
            onBack = { viewModel.navBack(openAndPopUp)},
            saveAndExit = {viewModel.onEvent(EditWorkoutEvent.SaveWorkout)},
            reps = reps,
            weight = weight,
            note = note,
            onRepsChange = {newValue -> viewModel.onEvent(EditWorkoutEvent.EnteredReps(newValue))},
            onWeightChange = {newValue -> viewModel.onEvent(EditWorkoutEvent.EnteredWeight(newValue))},
            onNoteChange = { newValue -> viewModel.onEvent(EditWorkoutEvent.EnteredNote(newValue))},
            repFocusChange = {repFocusChange -> viewModel.onEvent(EditWorkoutEvent.ChangeRepsFocus(repFocusChange))},
            weightFocusChange = {newFocusChange -> viewModel.onEvent(EditWorkoutEvent.ChangeWeightFocus(newFocusChange))},
            noteFocusChange = {noteFocusChange -> viewModel.onEvent(EditWorkoutEvent.ChangeNoteFocus(noteFocusChange))},
            onDelete = { viewModel.onEvent(EditWorkoutEvent.DeleteWorkout) },
            onDateChange = { newDate -> viewModel.onEvent(EditWorkoutEvent.ChangeDate(newDate)) } // Pass the date change event
        )
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditWorkoutContent(
state: EditWorkoutState,
onBack: () -> Unit,
saveAndExit: () -> Unit,
reps: BasicTextFieldState,
weight: BasicTextFieldState,
note: BasicTextFieldState,
onRepsChange: (String) -> Unit,
onWeightChange: (String) -> Unit,
onNoteChange: (String) -> Unit,
repFocusChange: (FocusState) -> Unit,
weightFocusChange: (FocusState) -> Unit,
noteFocusChange: (FocusState) -> Unit,
onDelete: () -> Unit,
onDateChange: (LocalDateTime) -> Unit
){
    Scaffold(
        // in the top bar back arrow and save icon.
        topBar = {
            state.workout?.let {
                TopToolbar_TextButtonTitleTextButton(
                    modifier = Modifier,
                    title = it.name,
                    primaryAction = { onBack() },
                    secondaryAction = { saveAndExit() }
                )
            }
        },
        content = {
    Column(
        modifier = Modifier.padding(16.dp)
    ){

        // reps
        Text(text = "Reps", style = MaterialTheme.typography.caption)
        TransparentHintField(
            text = reps.text,
            hint = reps.hint,
            isHintVisible = reps.isHintVisible,
            onValueChange = {onRepsChange(it)},
            textStyle = MaterialTheme.typography.subtitle1,
            onFocusChange = repFocusChange
        )
        Spacer(Modifier.height(16.dp))
        // weight
        Text(text = "Weight", style = MaterialTheme.typography.caption)
        TransparentHintField(
            text = weight.text,
            hint = weight.hint,
            isHintVisible = weight.isHintVisible,
            onValueChange = {onWeightChange(it)},
            textStyle = MaterialTheme.typography.subtitle1,
            onFocusChange = weightFocusChange
        )
        // date
        Spacer(Modifier.height(16.dp))

            WorkoutDatePicker(
                selectedDate = state.date,
                onDateSelected = onDateChange
            )

        Spacer(Modifier.height(16.dp))
        // notes
        Text(text = "Comments", style = MaterialTheme.typography.caption)
        CommentHintField(
            text = note.text,
            hint = note.hint,
            onValueChange = {onNoteChange(it)},
            isHintVisible = note.isHintVisible,
            textStyle = MaterialTheme.typography.subtitle1,
            onFocusChange = noteFocusChange,
            minLines = 3
        )


        Spacer(Modifier.height(20.dp))
        // delete
        DeleteIconButton (onDelete)
    }
        })

}
