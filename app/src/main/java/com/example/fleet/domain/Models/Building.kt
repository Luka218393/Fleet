package com.example.fleet.domain.Models

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import org.intellij.lang.annotations.Language
import java.sql.Date
import java.time.LocalDate

@Entity(tableName = "buildings")
data class Building (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String? = null,
    val floors: Int = 1,
    val numberOfApartments: Int = 1,
    val planRes: Int? = null,
    val location: Location? = null,
    val creationYear: Int = 1900,
    val addedDate: LocalDate = LocalDate.now(),
    val country: Countries = Countries.ENGLAND,
    val city: String? = null,
    var tenantLeaderContact: String? = null
)