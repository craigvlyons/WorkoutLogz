package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutlogz.R
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ExperimentalMaterialApi
fun BasicToolbar(@StringRes title: Int) {
  TopAppBar(title = { Text(stringResource(title)) }, colors = toolbarColor())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(
  modifier: Modifier,
  @StringRes title: Int,
  @DrawableRes primaryActionIcon: Int,
  primaryAction: () -> Unit,
  @DrawableRes secondaryActionIcon: Int? = null,
  secondaryAction: (() -> Unit)? = null
) {
  TopAppBar(
    title = { Text(stringResource(title)) },
    colors = toolbarColor(),
    actions = {
      Box(modifier) {
        Row(
          modifier = Modifier.wrapContentSize(),
        ) {
          IconButton(onClick = primaryAction) {
            Icon(painter = painterResource(primaryActionIcon), contentDescription = "Primary Action")
          }
          if (secondaryAction != null && secondaryActionIcon != null) {
            IconButton(onClick = secondaryAction) {
              Icon(painter = painterResource(secondaryActionIcon), contentDescription = "Secondary Action")
            }
          }
        }
      }
    }
  )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolbar_IconTitleIcon(
  modifier: Modifier,
  @StringRes title: Int,
  @DrawableRes primaryActionIcon: Int,
  primaryAction: () -> Unit,
  @DrawableRes secondaryActionIcon: Int? = null,
  secondaryAction: (() -> Unit)? = null
) {
  TopAppBar(
    modifier = Modifier,
    colors = toolbarColor(),
    title = {
      Row(
        modifier = modifier
          .padding(start = 0.dp, end = 16.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        // Left Button
        IconButton(onClick = primaryAction) {
          Icon(painter = painterResource(id = primaryActionIcon), contentDescription = "Back")
        }
        Spacer(Modifier.weight(1f))
        // Title in the Middle
        Text(text = stringResource(title))
        Spacer(modifier.weight(1f))
        // Right Button
        if (secondaryAction != null && secondaryActionIcon != null){
          IconButton(onClick = secondaryAction) {
            Icon(painter = painterResource(id = secondaryActionIcon), contentDescription = "Right Button")
          }
        }
      }
    }
  )
}

@Preview()
@Composable
fun preview_TopToolbar_IconTitleIcon() {
  TopToolbar_IconTitleIcon(
    Modifier,
    title = R.string.app_name,
    primaryActionIcon = R.drawable.ic_menu,
    primaryAction = {/* */ },
    secondaryActionIcon = R.drawable.ic_menu,
    secondaryAction = {/* */ }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): TopAppBarColors {
  val containerColor = if (darkTheme) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary

  // Assuming you want the same color for all states for simplicity
  return TopAppBarDefaults.topAppBarColors(
    containerColor = containerColor,
    scrolledContainerColor = containerColor,
    titleContentColor = MaterialTheme.colorScheme.onPrimary,
    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
  )
}
