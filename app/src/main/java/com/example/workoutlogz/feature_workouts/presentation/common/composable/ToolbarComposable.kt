package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutlogz.R
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


@Composable
@ExperimentalMaterialApi
fun BasicToolbar(@StringRes title: Int) {
  TopAppBar(title = { Text(stringResource(title)) }, contentColor = MaterialTheme.colors.primary)
}


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
    contentColor = MaterialTheme.colors.primary,
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
    contentColor = MaterialTheme.colors.primary,
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




