package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import java.time.LocalDate

@Entity(tableName = "buildings")
data class Building (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val country: Countries? = null,
    val region: String? = null,
    val city: String? = null,
    val address: String? = null,
    //val location: Location? = null, TODO add typeconverter for this type
    val floors: Int = 1,
    val numberOfApartments: Int = 1,
    var tenantLeaderContact: String? = null,
    val joinedDate: LocalDate = LocalDate.now(),
    val creationYear: Int? = null,
)
//Todo Add variables interesting to sellers