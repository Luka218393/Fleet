package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import com.example.fleet.domain.Enums.Theme
import java.util.UUID


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
data class Settings (//Rename to applicationSettings -> move everything to tenant add isOpenedOnStart
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val tenantId: String,
    var theme: Theme = Theme.DEFAULT,
    var showAnimation: Boolean = true,
    var language: Countries = Countries.ENGLAND,
    var showNotifications: Boolean = true,
    var immersiveMode: Boolean = false,
    var appColor: String = "18444365946022789120"

)

