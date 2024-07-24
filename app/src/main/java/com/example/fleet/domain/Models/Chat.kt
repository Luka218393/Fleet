package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats",)
data class Chat (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var profileImageResId: Int? = null,
    var isPrivate: Boolean,
)