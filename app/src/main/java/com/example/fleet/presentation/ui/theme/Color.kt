package com.example.fleet.presentation.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color



val Black = Color(0xFF000000)

val Green10 = Color(0xFFA2DD23)
val Green40 = Color(0xFF678B18)
val Green60 = Color(0xFF35480c)
val Green90 = Color(0xFF283609)


val Red10 = Color(0xFF510503)
val Red40 = Color(0xFF510503)
val Red60 = Color(0xFF510503)
val Red90 = Color(0xFF300302)

val Orange10 = Color(0xFFF8CD9E)
val Orange50 = Color(0xFFa97844)
val Orange70 = Color(0xFF72512E)
val Orange90 = Color(0xFF3D2B18)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF34113F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF634A74),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFFF9F23E),
    onSecondary = Black,
    secondaryContainer = Color(0xFFD1CE7F),
    onSecondaryContainer = Black,


    tertiary = Color(0xff8E8E8E),
    onTertiary = Black,
    tertiaryContainer = Color(0xff8E8E8E),
    onTertiaryContainer = Color(0xff8E8E8E),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF9F23E),
    onPrimary = Black,
    primaryContainer = Color(0xFFD1CE7F),
    onPrimaryContainer = Black,

    secondary = Color(0xFF34113F),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF634A74),
    onSecondaryContainer = Color.White,

    tertiary = Color(0xff8E8E8E),
    onTertiary = Black,
    tertiaryContainer = Color(0xff8E8E8E),
    onTertiaryContainer = Color(0xff8E8E8E),

    )