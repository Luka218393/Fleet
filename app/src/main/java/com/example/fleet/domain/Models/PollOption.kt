package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val pollId: Int, //
    val value: String,
    var votes: List<Int> = emptyList(),
)
