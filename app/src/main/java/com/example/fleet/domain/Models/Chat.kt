package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.ChatType

@Entity(tableName = "chats",)
data class Chat (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String? = null,//make this non nullable
    var profileImageResId: Int? = null,
    var isPrivate: Boolean = true,
    var chatType: ChatType,
)