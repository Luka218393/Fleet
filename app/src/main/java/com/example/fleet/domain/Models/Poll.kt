package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Enums.Voters
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "polls",
    foreignKeys = [
        ForeignKey(entity = Tenant::class, parentColumns = ["id"], childColumns = ["creatorId"])
    ]
)
data class Poll (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //@ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["creatorId"])
    val creatorId: Int,
    val dateCreated: LocalDate = LocalDate.now(),
    val title: String,
    val question: String,
    val pollType: PollType,
    val voteEndDate: Date,
    val isPublic: Boolean = true,
)