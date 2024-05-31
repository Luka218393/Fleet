package com.example.fleet.domain.Models

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import org.intellij.lang.annotations.Language
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "buildings")
data class Building (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String? = null,
    val floors: Int = 1,
    val numberOfApartments: Int = 1,
    val planRes: Int? = null,
    //val location: Location? = null, TODO add typeconverter for this type
    val creationYear: Int? = null,
    val addedDate: Date = Date(),
    val country: Countries? = null,
    val city: String? = null,
    var tenantLeaderContact: String? = null
)