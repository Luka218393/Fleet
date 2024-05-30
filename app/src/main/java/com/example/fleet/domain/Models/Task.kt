package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "tasks",
    foreignKeys = [
        ForeignKey(
        entity = Tenant::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("creatorId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var completed: Boolean = false,
    val priority: Int = 0,//TODO Enum class
    //val assignedTo: Tenant? = null,
    val creatorId: Int,//
    var completedAt: Date? = null,
    val dueDate: Date = Date(),
    val createdAt: Date = Date(),
)