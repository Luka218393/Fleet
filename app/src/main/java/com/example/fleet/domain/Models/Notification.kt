package com.example.fleet.domain.Models

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.data.daos.Tenants
import java.time.LocalDate
import java.util.Date

@Entity(
    tableName = "notifications",
    foreignKeys = [
        ForeignKey(
            entity = Tenants::class,
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
    var iconResId: ImageVector,
    val createdAt: LocalDate = LocalDate.now(),
    //ForeignKey
    val creatorId: Int,
    var dueTo: Date? = null,
    var visibleToEveryone: Boolean = true
)