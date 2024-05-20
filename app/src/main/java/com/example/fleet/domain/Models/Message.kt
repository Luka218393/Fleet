package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.data.daos.Tenants
import java.time.LocalDate
import java.util.Date

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = Tenants::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val senderId: Int,
    var hash: String? = null,
    var sendingTime: LocalDate = LocalDate.now(),
    var profileImage: Int? = null,
    var imageUrl: String? = null,
)