package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tenant_chat",
    foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["tenantId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Chat::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TenantChat(
    val tenantId: String,
    val chatId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
