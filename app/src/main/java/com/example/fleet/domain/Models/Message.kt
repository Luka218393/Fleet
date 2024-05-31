package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val senderId: Int,//
    var text: String = "",
    var sendingTime: Date = Date(),
    var profileImage: Int? = null,
    var imageUrl: String? = null,
)