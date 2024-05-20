package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "tasks",
    foreignKeys = [ForeignKey(
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
    val priority: Int,//TODO Enum class
    val assignedTo: Tenant? = null,
    //Foreign key
    val creatorId: Int,
    var completedAt: LocalDate? = null,
    val dueDate: LocalDate? = LocalDate.now(),
    val createdAt: LocalDate = LocalDate.now(),
)