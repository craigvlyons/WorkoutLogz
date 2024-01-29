package com.example.workoutlogz.ui.theme


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColors(
    primary= DarkBackground  ,
    primaryVariant  = LightBackground, // buttons
    secondary = DividerColor,
    secondaryVariant = LightGreen, // buttons
    background = DarkerBackground,
    surface = LightBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
)

/*
val White = Color(0xFFFFFFFF)

val DarkGreen = Color(0xFF1EB980)
val LightGreen = Color(0xFF4CAF50)
val DarkerBackground = Color(0xFF121212) // Or any color you want for the background
val DarkBackground = Color(0xFF2C2C2C) // Background for items
val LightBackground = Color(0xFF3C3C3C) // Background for buttons
val DividerColor = Color(0xFF616161)
 */

private val LightColorScheme = lightColors(
    primary= DarkBackground  ,
    primaryVariant = LightBackground,
    secondary = DividerColor,
    secondaryVariant = LightGreen,
    background = DarkerBackground,
    surface = LightBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
)

@Composable
fun WorkoutLogzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}