package com.example.fleet.domain.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.fleet.R
import java.time.LocalDate
import java.util.Date
import java.util.UUID

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
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    var apartmentId: String, //
    var name: String,
    var surname: String = "",
    val phoneNumber: String? = null,
    var email: String? = null,
    var gender: String? = null,//Make enum and rest
    var profileImageRes: Int = R.drawable.account_icon,
    val birthday: Date? = null,
    var profession: String? = null,
    var description: String? = null,
    var isOnline: Boolean = true,
    var isApartmentHead: Boolean = false,
    var isTenantLeader: Boolean = false,
    val createdAt: LocalDate = LocalDate.now(),
    var password: String
)
//Todo add password