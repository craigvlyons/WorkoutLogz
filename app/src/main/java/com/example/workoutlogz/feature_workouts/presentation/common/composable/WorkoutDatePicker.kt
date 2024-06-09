package com.example.workoutlogz.feature_workouts.presentation.common.composable

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutDatePicker(
    selectedDate: LocalDateTime,
    onDateSelected: (LocalDateTime) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newDate = selectedDate.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
            onDateSelected(newDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            val newTime = selectedDate.withHour(hourOfDay).withMinute(minute)
            onDateSelected(newTime)
        },
        selectedDate.hour,
        selectedDate.minute,
        false
    )

    val formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
    val formattedTime = selectedDate.format(DateTimeFormatter.ofPattern("hh:mm a"))

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Date:", style = MaterialTheme.typography.subtitle1)
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.clickable { datePickerDialog.show() }
        )
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.clickable { timePickerDialog.show() }
        )
    }
}