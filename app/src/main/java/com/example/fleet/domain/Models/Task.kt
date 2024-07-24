package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "tasks",
    foreignKeys = [
        ForeignKey(
        entity = Tenant::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("creatorId"),
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Task(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val buildingId: String,//
    val creatorId: String,//
    var title: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)