package com.example.fleet.data.Models

import java.util.Date

data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var completed: Boolean = false,
    val dueDate: Date? = Date(),
    val priority: Int,//TODO Enum class
    val tags: List<String>,//TODO Enum class
    val assignedTo: Tenant?,
    val createdBy: Tenant,
    val createdAt: Date,
    var completedAt: Date?,
    var deletedAt: Date?

)