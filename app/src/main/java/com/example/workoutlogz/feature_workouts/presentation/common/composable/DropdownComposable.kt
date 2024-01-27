package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
@ExperimentalMaterialApi
fun DropdownContextMenu(
  options: List<String>,
  modifier: Modifier,
  onActionClick: (String) -> Unit
) {

}

@Composable
@ExperimentalMaterialApi
fun DropdownSelector(
  @StringRes label: Int,
  options: List<String>,
  selection: String,
  modifier: Modifier,
  onNewValue: (String) -> Unit
) {

}

@Composable
@ExperimentalMaterialApi
private fun dropdownColors(): TextFieldColors {
  return ExposedDropdownMenuDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.secondary,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    focusedTrailingIconColor = MaterialTheme.colors.onSurface,
    focusedLabelColor = MaterialTheme.colors.secondaryVariant,
    unfocusedLabelColor = MaterialTheme.colors.onPrimary
  )
}
