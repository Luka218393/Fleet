package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "chats",
    foreignKeys = [
        ForeignKey(
            entity = Message::class,
            parentColumns = ["id"],
            childColumns = ["lastMessageId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ])
data class Chat (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var profileImageResId: Int? = null,
    var lastMessageId: String? = null,//
    var isPrivate: Boolean,
)