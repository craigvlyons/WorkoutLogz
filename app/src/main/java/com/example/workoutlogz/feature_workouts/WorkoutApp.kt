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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.makeitso.common.snackbar.SnackbarManager
import com.example.workoutlogz.feature_workouts.presentation.add_workout_names.AddExerciseNamesScreen
import com.example.workoutlogz.feature_workouts.presentation.common.composable.PermissionDialog
import com.example.workoutlogz.feature_workouts.presentation.common.composable.RationaleDialog
import com.example.workoutlogz.feature_workouts.presentation.edit_exercise_list_screen.EditExerciseListScreen
import com.example.workoutlogz.feature_workouts.presentation.edit_workout_screen.EditWorkoutScreen
import com.example.workoutlogz.feature_workouts.presentation.exercise_app.ExerciseAppScreen
import com.example.workoutlogz.feature_workouts.presentation.exercise_list_screen.ExerciseListScreen
import com.example.workoutlogz.feature_workouts.presentation.new_exerciseList_screen.NewExerciseListScreen
import com.example.workoutlogz.feature_workouts.presentation.settings_screen.SettingScreen
import com.example.workoutlogz.feature_workouts.presentation.splashScreen.SplashScreen
import com.example.workoutlogz.feature_workouts.presentation.workout_screen.WorkoutScreen
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

    composable(ADD_EXERCISE_NAME_SCREEN) {
        AddExerciseNamesScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)}
        )
    }

    composable(NEW_EXERCISE_LIST_SCREEN) {
        NewExerciseListScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(
        route = "$EXERCISE_LIST_SCREEN/{$EXERCISELIST_ID}",
        arguments = listOf(navArgument(EXERCISELIST_ID) {
            type = NavType.IntType
            defaultValue = -1
        })
    ) { backStackEntry ->
        val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID)
        if (exerciseListId != null) {
            ExerciseListScreen(
                exerciseListId = exerciseListId,
                openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp) },
                openScreen = { route -> appState.navigate(route) }
            )
        }
    }

    composable(
        route = "$EDIT_EXERCISE_LIST_SCREEN/{$EXERCISELIST_ID}",
        arguments = listOf(navArgument(EXERCISELIST_ID) {
            type = NavType.IntType
            defaultValue = -1
        })
    ) { backStackEntry ->
        val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID)
        if (exerciseListId != null) {
            EditExerciseListScreen(
                exerciseListId = exerciseListId,
                openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp) },
            )
        }
    }

    composable(
        route = WORKOUT_SCREEN_ROUTE,
        arguments = listOf(
            navArgument(WORKOUT_NAME) { type = NavType.StringType },
            navArgument(EXERCISELIST_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        // Extract arguments
        val workoutName = backStackEntry.arguments?.getString(WORKOUT_NAME) ?: ""
        val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID) ?: -1
        // Use arguments as needed
        WorkoutScreen(
            openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp) },
            openScreen = {route -> appState.navigate(route)},
            exerciseListId,
            workoutName,
        )
    }

    composable(
        route = "$EDIT_WORKOUT_SCREEN/{$WORKOUT_ID}",
        arguments = listOf(
            navArgument(WORKOUT_ID) { type = NavType.IntType },
            //navArgument(EXERCISELIST_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        // Extract arguments
        val workoutId = backStackEntry.arguments?.getInt(WORKOUT_ID) ?: -1
        //val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID) ?: -1
        // Use arguments as needed
        EditWorkoutScreen(
            openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp) },
            //openScreen = {route -> appState.navigate(route)},
            workoutId = workoutId,
        )
    }
    composable(
        route = "$EDIT_WORKOUT_SCREEN/{$WORKOUT_ID}/{$EXERCISELIST_ID}",
        arguments = listOf(
            navArgument(WORKOUT_ID) { type = NavType.IntType },
            navArgument(EXERCISELIST_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        // Extract arguments
        val workoutId = backStackEntry.arguments?.getInt(WORKOUT_ID) ?: -1
        val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID) ?: -1
        // Use arguments as needed
        EditWorkoutScreen(
            openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp) },
            //openScreen = {route -> appState.navigate(route)},
            workoutId = workoutId,
            exerciseListId = exerciseListId
        )
    }


}

//    composable(SIGN_UP_SCREEN) {
//        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(TASKS_SCREEN) {
//        TasksScreen(openScreen = { route -> appState.navigate(route) })
//    }
//
//    composable(EXERCISE_LIST_SCREEN) {
//            ExerciseListScreen(popUpScreen = { appState.popUp() })
//    }

//    composable(
//        route = "$EXERCISE_LIST_SCREEN$EXERCISELIST_ID_ARG",
//        arguments = listOf(navArgument(EXERCISELIST_ID) {
//            type = NavType.IntType
//            defaultValue = -1
//        })
//    ) { //backStackEntry ->
//        //val exerciseListId = backStackEntry.arguments?.getInt(EXERCISELIST_ID)
//        ExerciseListScreen(
//            //exerciseListId = exerciseListId,
//            popUpScreen = { appState.popUp() }
//        )
//    }



