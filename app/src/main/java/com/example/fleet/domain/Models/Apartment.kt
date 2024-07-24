package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "apartments",
    foreignKeys = [
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"]
        ),
    ]
)
data class Apartment (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val buildingId: Int,//
    val floor: Int = 0,
    val door: String = "1",
    val maxCapacity: Int? = null,
    val areaInMeters2: Int? = null,
    val numberOfRooms: Int? = null,
    val hasPets: Boolean? = null,
)