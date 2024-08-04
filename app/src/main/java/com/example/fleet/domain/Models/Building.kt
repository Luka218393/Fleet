package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fleet.domain.Enums.Countries
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "buildings",
    /*foreignKeys = [
        ForeignKey(
            entity = Tenant::class,
            parentColumns = ["id"],
            childColumns = ["tenantLeaderId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]*/

)
data class Building (
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val country: Countries,
    val region: String,
    val city: String,
    val address: String,
    //val location: Location? = null, TODO add typeconverter for this type
    val floors: Int = 1,
    val numberOfApartments: Int = 1,
    //var tenantLeaderId: String,
    val joinedDate: LocalDate = LocalDate.now(),
    val creationYear: Int? = null,
)
//Todo Add variables interesting to sellers
/*
* has Lift,
* parking spots
* */