package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import com.example.fleet.domain.Enums.FontSize
import com.example.fleet.domain.Enums.Theme

@Entity(
    tableName = "settings",
    foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["tenantId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Apartment::class,
            parentColumns = ["id"],
            childColumns = ["apartmentId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Building::class,
            parentColumns = ["id"],
            childColumns = ["buildingId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
)
data class Settings (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tenantId: Int = 0,//
    val buildingId: Int = 0,//
    val apartmentId: Int = 0,//
    var theme: Theme = Theme.DEFAULT,
    var font: FontSize = FontSize.MEDIUM,
    var showAnimation: Boolean = true,
    var language: Countries = Countries.ENGLAND,
    var showNotifications: Boolean = true,

    //var appColor: Color
)

