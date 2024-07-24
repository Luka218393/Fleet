package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.PollType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity(
    tableName = "polls",
    foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["creatorId"]
        ),
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Poll (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val buildingId: String,//
    val creatorId: String,//
    val title: String,
    val pollType: PollType,
    var resultsVisible: Boolean = true,
    val endDate: LocalDate = LocalDate.now().plusDays(2),
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    )
//Todo rename voteEndDate to endDate