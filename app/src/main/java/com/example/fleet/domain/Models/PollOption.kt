package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "poll_options",
    foreignKeys = [
        ForeignKey(
            entity = Poll::class,
            parentColumns = ["id"],
            childColumns = ["pollId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
data class PollOption (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val pollId: String, //
    val value: String,
    var votes: List<String> = emptyList(),
)
