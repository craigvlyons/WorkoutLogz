package com.example.workoutlogz.feature_workouts

import android.Manifest
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.makeitso.common.snackbar.SnackbarManager
import com.example.workoutlogz.feature_workouts.presentation.common.composable.PermissionDialog
import com.example.workoutlogz.feature_workouts.presentation.common.composable.RationaleDialog
import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseAppScreen
import com.example.workoutlogz.feature_workouts.presentation.settings_screen.SettingScreen
import com.example.workoutlogz.feature_workouts.presentation.splashScreen.SplashScreen
import com.example.workoutlogz.ui.theme.WorkoutLogzTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.CoroutineScope

@Composable
fun WorkoutApp(){
    WorkoutLogzTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            RequestNotificationPermissionDialog()
        }
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = androidx.compose.ui.Modifier.padding(innerPaddingModifier)
                ) {
                    WorkoutGraph(appState)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermissionDialog() {
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    if (!permissionState.status.isGranted) {
        if (permissionState.status.shouldShowRationale) RationaleDialog()
        else PermissionDialog { permissionState.launchPermissionRequest() }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        WorkoutAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.WorkoutGraph(appState: WorkoutAppState){
    composable(SPLASH_SCREEN){
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(EXERCISE_APP_SCREEN){
        ExerciseAppScreen(openScreen = {route -> appState.navigate(route)})
    }

    composable(SETTINGS_SCREEN) {
        SettingScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)}
//            restartApp = { route -> appState.clearAndNavigate(route) },
//            openScreen = { route -> appState.navigate(route) }
        )
    }
//
//    composable(STATS_SCREEN) {
//        StatsScreen()
//    }
//
//    composable(LOGIN_SCREEN) {
//        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(SIGN_UP_SCREEN) {
//        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(TASKS_SCREEN) {
//        TasksScreen(openScreen = { route -> appState.navigate(route) })
//    }
//
//    composable(
//        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
//        arguments = listOf(navArgument(TASK_ID) {
//            nullable = true
//            defaultValue = null
//        })
//    ) {
//        EditTaskScreen(
//            popUpScreen = { appState.popUp() }
//        )
//    }


}