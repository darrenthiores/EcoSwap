package com.darrenthiores.ecoswap.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun EcoSwapTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Primary,
            secondary = Secondary,
            background = Black,
            surface = Black,
            onPrimary = White,
            onSecondary = White,
            onBackground = White,
            onSurface = White,
            onError = White
        )
    } else {
        lightColors(
            primary = Primary,
            secondary = Secondary,
            background = White,
            surface = White,
            onPrimary = White,
            onSecondary = White,
            onBackground = Black,
            onSurface = Black,
            onError = White
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
