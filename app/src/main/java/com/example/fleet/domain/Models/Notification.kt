package com.example.fleet.domain.Models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(
    tableName = "notifications",
    foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["creatorId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Notification (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var text: String,
    var imageResId: Int? = null,
    var iconResId: ImageVector = Icons.Default.Add,
    val createdAt: LocalDate = LocalDate.now(),
    val creatorId: Int, //
    var dueTo: Date? = null,
    var visibleToEveryone: Boolean = true
)