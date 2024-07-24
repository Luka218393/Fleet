package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
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
        )
    ],
)
data class Settings (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tenantId: Int = 0,
    var theme: Theme = Theme.DEFAULT,
    var showAnimation: Boolean = true,
    var language: Countries = Countries.ENGLAND,
    var showNotifications: Boolean = true,

    var appColor: String = "18444365946022789120"

)

