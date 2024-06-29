package com.example.fleet.presentation.ui.theme

import androidx.compose.ui.graphics.Color


data class ColorScheme(
    var primary: Color,
    val onPrimary: Color,
    var secondary: Color,
    var onSecondary: Color,
    var tertiary: Color,
    var onTertiary: Color,
    var background: Color
)

val DarkScheme = ColorScheme(
    primary = Color(0xFF34113F),
    onPrimary = Color.White,

    secondary = Color(0xFFF78D1B),
    onSecondary = Black,

    tertiary = Color(0xff8E8E8E),
    onTertiary = Black,

    background = Color(0xFF1F1F1F),
)