package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "SubTasks",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("taskId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["completedBy"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var text: String,
    var completed: Boolean = false,
    val taskId: Int,
    var completedAt: Date? = null,
    var completedBy: Int? = null
)