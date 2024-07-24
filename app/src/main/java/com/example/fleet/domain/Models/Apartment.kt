package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "apartments",
    foreignKeys = [
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"]
        ),
    ]
)
/*Todo
@PrimaryKey(autoGenerate = false)
val id: String = UUID.randomUUID().toString(),*/
data class Apartment (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val buildingId: String,//
    val floor: Int = 0,
    val door: String = "1",
    val maxCapacity: Int? = null,
    val areaInMeters2: Int? = null,
    val numberOfRooms: Int? = null,
    val hasPets: Boolean? = null,
)