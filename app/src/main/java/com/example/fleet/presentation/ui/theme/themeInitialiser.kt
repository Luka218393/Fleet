package com.example.fleet.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.fleet.FleetApplication
import com.example.ui.theme.AppTypography

var Black = Color(0,0,0)
@Composable
fun FleetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {

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
        typography = AppTypography,
        content = content,
    )
}
/*val darkScheme = remember(FleetApplication.fleetModule.appColor) { // Recalculate when appColor changes
        darkColorScheme(
            primary = Color(0xFF34113F),
            onPrimary = White,
            secondary = FleetApplication.fleetModule.appColor,
            onSecondary = White,
            tertiary = Color(0xff8E8E8E),
            onTertiary = White,
            background = Color(0xFF1F1F1F),
        )
    }

    val lightScheme = remember(FleetApplication.fleetModule.appColor) { // Recalculate when appColor changes
        lightColorScheme(
            primary = Color(0xFF34113F),
            onPrimary = Black,
            secondary = FleetApplication.fleetModule.appColor,
            onSecondary = White,
            tertiary = Color(0xff8E8E8E),
            onTertiary = Black,
            background = Color(0xFFCFCECE),
        )
    }*/