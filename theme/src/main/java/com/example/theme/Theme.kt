package com.example.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = GreyField,
    secondary = AppBlue,

    )

private val LightColorPalette = lightColors(
    primary = AppBlue,
    primaryVariant = GreyField,
    secondary = DarkBlue,
    background = Background,
    onBackground = AppBlack,
    secondaryVariant = GreyText,
    onPrimary = AppWhite,
    onSurface = White
)

@Composable
fun Test_task_4Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )


}