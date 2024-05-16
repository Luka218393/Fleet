package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poll_options")
data class PollOption (
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val value: String = "",
    var votes: Int = 0
)
