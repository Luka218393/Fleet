package com.example.fleet.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF34113F),
    onPrimary = White,
    primaryContainer = Color(0xFF634A74),
    onPrimaryContainer = White,

    secondary = Color(0xFFF9F23E),
    onSecondary = Black,
    secondaryContainer = Color(0xFFDAD435),//Color(0xFFB8B45D),
    onSecondaryContainer = Black,

    tertiary = Color(0xff8E8E8E),
    onTertiary = Black,
    tertiaryContainer = Color(0xFFB8B7B7),
    onTertiaryContainer = Black,

    background = Black,
    )

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF9F23E),
    onPrimary = Black,
    primaryContainer = Color(0xFFFAF69D),
    onPrimaryContainer = Black,

    secondary = Color(0xFF34113F),
    onSecondary = White,
    secondaryContainer = Color(0xFF634A74),
    onSecondaryContainer = White,

    tertiary = Color(0xff8E8E8E),
    onTertiary = Black,
    tertiaryContainer = Color(0xFFB8B7B7),
    onTertiaryContainer = Black,

    background = Color(0xFFCFCECE),
)

@Composable
fun FleetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}