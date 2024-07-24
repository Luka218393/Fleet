package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.PollType
import java.time.LocalDate
import java.time.LocalDateTime

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
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val buildingId: Int,//
    val creatorId: Int,//
    val title: String,
    val pollType: PollType,
    var resultsVisible: Boolean = true,
    val endDate: LocalDate = LocalDate.now().plusDays(2),
    val dateCreated: LocalDateTime = LocalDateTime.now(),
    )
//Todo rename voteEndDate to endDate