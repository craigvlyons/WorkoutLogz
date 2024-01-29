package com.example.workoutlogz.feature_workouts.presentation.settings_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutlogz.R
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TopToolbar_IconTitleIcon
import com.example.workoutlogz.feature_workouts.presentation.common.composable.TransparentHintField
import org.w3c.dom.Text


@Composable
fun SettingScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {

    SettingsScreenContent(
        onBack = {viewModel.navBack(openAndPopUp)}
    )
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreenContent(
    onBack: () -> Unit,
    ) {
    Scaffold(
        topBar = {
            TopToolbar_IconTitleIcon(
                modifier = Modifier,
                primaryActionIcon = R.drawable.ic_menu,
                title = R.string.SettingsTitle,
                primaryAction = { onBack() },
                secondaryActionIcon = null,
                secondaryAction = { /* Handle secondary action here */ }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        content = {

            Column {

                Button(onClick = {  }) {
                    Text("Add Exercise")
                }
            }
        }
    )
}


