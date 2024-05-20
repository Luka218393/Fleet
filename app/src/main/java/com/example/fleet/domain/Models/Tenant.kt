package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tenants",
    foreignKeys = [ForeignKey(
        entity = Apartment::class,
        parentColumns = ["id"],
        childColumns = ["apartmentId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )    ]
)
data class Tenant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var age: Int? = null,
    var apartmentId: Int,
    val phone: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var profileImageRes: Int? = null,
    val birthday: LocalDate? = null,
    val createdAt: LocalDate? = LocalDate.now(),
    var profession: String? = null,
    var aboutMe: String? = null,
    var isOnline: Boolean = true,
    var isApartmentHead: Boolean = false,
    var isTenantLeader: Boolean = false,
)