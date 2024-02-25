package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
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
        contentColor = MaterialTheme.colors.background,
        actions = {
            Box(modifier) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                ) {
                    IconButton(onClick = primaryAction) {
                        Icon(
                            painter = painterResource(primaryActionIcon),
                            contentDescription = "Primary Action"
                        )
                    }
                    if (secondaryAction != null && secondaryActionIcon != null) {
                        IconButton(onClick = secondaryAction) {
                            Icon(
                                painter = painterResource(secondaryActionIcon),
                                contentDescription = "Secondary Action"
                            )
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
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Row(
                modifier = modifier
                    .padding(start = 0.dp, end = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Button
                // First item, left-aligned
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = primaryAction) {
                        Icon(
                            painter = painterResource(id = primaryActionIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }

                // Second item, centered
                Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
                    // Title in the Middle
                    Text(text = stringResource(title))
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    // Right Button
                    if (secondaryAction != null && secondaryActionIcon != null) {
                        IconButton(onClick = secondaryAction) {
                            Icon(
                                painter = painterResource(id = secondaryActionIcon),
                                contentDescription = "Right Button"
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun TopToolbar_TitleIcon(
    modifier: Modifier,
    @StringRes title: Int,
    @DrawableRes primaryActionIcon: Int,
    primaryAction: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Row(
                modifier = modifier
                    .padding(start = 0.dp, end = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Button
                // First item, left-aligned
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {

                }

                // Second item, centered
                Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
                    // Title in the Middle
                    Text(text = stringResource(title))
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    IconButton(onClick = primaryAction) {
                        Icon(
                            painter = painterResource(id = primaryActionIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }

                }
            }
        }
    )
}


@Composable
fun TopToolbar_DynamicTitle(
    modifier: Modifier,
    title: String,
    @DrawableRes primaryActionIcon: Int,
    primaryAction: () -> Unit,
    @DrawableRes secondaryActionIcon: Int? = null,
    secondaryAction: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Row(
                modifier = modifier
                    .padding(start = 0.dp, end = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Button
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = primaryAction) {
                        Icon(
                            painter = painterResource(
                                id = primaryActionIcon
                            ),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    // Title in the Middle
                    Text(text = title)
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    // Right Button
                    if (secondaryAction != null && secondaryActionIcon != null) {
                        IconButton(onClick = secondaryAction) {
                            Icon(
                                painter = painterResource(id = secondaryActionIcon),
                                contentDescription = "Right Button",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colors.secondaryVariant
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun TopToolbar_TextButtonTitleTextButton(
    modifier: Modifier,
    title: String,
    primaryAction: () -> Unit,
    secondaryAction: () -> Unit
) {
    TopAppBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Row(
                modifier = modifier
                    .padding(start = 0.dp, end = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    CancelTextButton(text = "Cancel") {
                        primaryAction()
                    }
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(text = title)
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    TextButton(onClick = { secondaryAction() }) {
                        Text(
                            text = "Done",
                            color = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
            }
        }
    )
}

@Preview()
@Composable
fun preview_TopToolbar_IconTitleIcon() {
    Column {
        TopToolbar_TitleIcon(
            modifier = Modifier,
            title = R.string.app_name,
            primaryActionIcon = R.drawable.settings,
            primaryAction = { }
        )
        TopToolbar_IconTitleIcon(
            Modifier,
            title = R.string.app_name,
            primaryActionIcon = R.drawable.ic_menu,
            primaryAction = {/* */ },
            secondaryActionIcon = R.drawable.edit,
            secondaryAction = {/* */ }
        )
        TopToolbar_TextButtonTitleTextButton(
            modifier = Modifier,
            title = "Exercise List",
            primaryAction = {  },
            secondaryAction = { }
        )
            

    }
}




