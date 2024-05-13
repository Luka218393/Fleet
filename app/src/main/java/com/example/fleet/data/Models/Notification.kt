package com.example.fleet.data.Models

import androidx.compose.ui.graphics.vector.ImageVector

data class Notification (
    val id: Int,
    val iconResId: ImageVector,
    val title: String,
    val text: String,
    val imageResId: Int? = null
)