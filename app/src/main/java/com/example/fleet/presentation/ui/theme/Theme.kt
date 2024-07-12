package com.example.fleet.presentation.ui.theme

import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Models.Settings

var Black = Color(0,0,0)/*
fun  darkScheme (settings: MutableState<Settings>): ColorScheme {
    return darkColorScheme(
        primary = Color(0xFF34113F),
        onPrimary = White,

        secondary = Color(settings.value.appColor.toULong()),
        onSecondary = Black,

        tertiary = Color(0xff8E8E8E),
        onTertiary = Black,

        background = Color(0xFF1F1F1F),
    )
}

fun  lightScheme (settings: MutableState<Settings>): ColorScheme{
    return lightColorScheme(
        primary = Color(0xFF34113F),
        onPrimary = Black,
        secondary = Color(settings.value.appColor.toULong()),
        onSecondary = White,

        tertiary = Color(0xff8E8E8E),
        onTertiary = Black,

        background = Color(0xFFCFCECE),
    )
}
*/
@Composable
fun FleetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
    settings: Settings
) {

    val darkScheme = remember(settings.appColor) { // Recalculate when appColor changes
        darkColorScheme(
            primary = Color(0xFF34113F),
            onPrimary = White,
            secondary = Color(settings.appColor.toULong()),
            onSecondary = Black,
            tertiary = Color(0xff8E8E8E),
            onTertiary = Black,
            background = Color(0xFF1F1F1F),
        )
    }

    val lightScheme = remember(settings.appColor) { // Recalculate when appColor changes
        lightColorScheme(
            primary = Color(0xFF34113F),
            onPrimary = Black,
            secondary = Color(settings.appColor.toULong()),
            onSecondary = White,
            tertiary = Color(0xff8E8E8E),
            onTertiary = Black,
            background = Color(0xFFCFCECE),
        )
    }
    Log.i("FleetModule","Theme recomposition")
    val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = FleetApplication.fleetModule.context
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(
                    context
                )
            }

            darkTheme -> darkScheme
            else -> lightScheme
        }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(settings = settings),
        content = content,
    )
}