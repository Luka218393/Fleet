package com.example.fleet.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.fleet.FleetApplication

var Black = Color(0,0,0)
var DarkScheme = mutableStateOf(
    darkColorScheme(
        primary = Color(0xFF34113F),
        onPrimary = White,

        secondary = FleetApplication.fleetModule.appColor,
        onSecondary = Black,

        tertiary = Color(0xff8E8E8E),
        onTertiary = Black,

        background = Color(0xFF1F1F1F),
    )
)

var LightScheme = mutableStateOf(
    lightColorScheme(
        primary = Color(0xFF34113F),
        onPrimary = Black,

        secondary = FleetApplication.fleetModule.appColor,
        onSecondary = White,

        tertiary = Color(0xff8E8E8E),
        onTertiary = Black,

        background = Color(0xFFCFCECE),
    )
)

@Composable
fun FleetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
    darkScheme: MutableState<ColorScheme>,
    lightScheme: MutableState<ColorScheme>
) {

    val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = FleetApplication.fleetModule.context
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(
                    context
                )
            }

            darkTheme ->darkScheme.value
            else -> lightScheme.value
        }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )

}