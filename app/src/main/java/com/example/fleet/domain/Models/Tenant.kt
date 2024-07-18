package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tenants",
    foreignKeys = [
        ForeignKey(
        entity = Apartment::class,
        parentColumns = ["id"],
        childColumns = ["apartmentId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )    ]
)
//Todo add color so that everyone can see your color
data class Tenant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var surname: String = "",
    var age: Int? = null,
    var apartmentId: Int, //
    val phoneNumber: String? = null,
    var email: String? = null,
    var gender: String? = null,//Make enum and rest
    var profileImageRes: Int? = null,
    val birthday: Date? = null,
    val createdAt: Date = Date(),
    var profession: String? = null,
    var aboutMe: String? = null,//Todo Rename this to description
    var isOnline: Boolean = true,
    var isApartmentHead: Boolean = false,
    var isTenantLeader: Boolean = false,
)