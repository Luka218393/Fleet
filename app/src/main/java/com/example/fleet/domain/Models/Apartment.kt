package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apartments")
data class Apartment (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val building: Int/*Todo Building,*/,
    var entrance: String? = "Main entrance",
    val floor: Int,
    val door: String,
    val tenants: List<Tenant> = emptyList(),
    val maxCapacity: Int? = null,
    val areaInMeters2: Int? = null,
    val planRes: Int? = null,
    val numberOfRooms: Int,
    val hasPets: Boolean = false,
    val notes: String? = null,
)