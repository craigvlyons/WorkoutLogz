package com.example.workoutlogz.feature_workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeitso.common.snackbar.SnackbarManager
import com.example.makeitso.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class WorkoutViewModel() : ViewModel() {
        fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
            viewModelScope.launch(
                CoroutineExceptionHandler { _, throwable ->
                    if (snackbar) {
                        SnackbarManager.showMessage(throwable.toSnackbarMessage())
                    }

                },
                block = block
            )
    }

