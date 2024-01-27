package com.example.workoutlogz.feature_workouts

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.example.makeitso.common.snackbar.SnackbarManager
import com.example.makeitso.common.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class WorkoutAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        // Launch a coroutine for snackbar message handling
        coroutineScope.launch {
            // Listen to snackbar messages, filter out nulls, and process each message
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                // Convert the message to a displayable format
                val text = snackbarMessage.toMessage(resources)
                // Show the message in the snackbar
                scaffoldState.snackbarHostState.showSnackbar(text)
                // Clear the snackbar state after displaying the message
                snackbarManager.clearSnackbarState()
            }
        }
    }

    // Function to pop the topmost route from the navigation stack
    fun popUp() {
        navController.popBackStack()
    }

    // Function to navigate to a specified route
    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }// Ensures no multiple copies of the same destination
    }

    // Function to navigate to a route and remove a specified route from the stack
    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    // Function to clear the entire navigation stack up to the root and navigate to a specified route
    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }



}