package com.example.fleet.domain.Models

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class Notification (
    val id: Int,
    var title: String,
    var text: String,
    var imageResId: Int? = null,
    var iconResId: ImageVector,
    val createdAt: Date,
    val createdBy: Tenant,
    var dueTo: Date? = null,
    var visibleToEveryone: Boolean = true
)