package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "chats",)
data class Chat (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var profileImageResId: Int? = null,
    var isPrivate: Boolean,
)