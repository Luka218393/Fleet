package com.example.fleet.data

import android.location.Location
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.TypeConverter
import com.example.fleet.domain.Enums.Icono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class TypeConverte {
    @TypeConverter
    fun dateToLong(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun longtoDate(long: Long):Date{
        return Date(long)
    }
    @TypeConverter
    fun fromIcon(icon: ImageVector): String {
        return Icono.entries.find { icono -> icono.nomen == icon.name }.toString()
    }

    @TypeConverter
    fun toIcon(iconName: String?): ImageVector {
        return Icono.entries.find { icono -> icono.name == iconName }?.imageVector ?: Icons.Filled.ExitToApp
    }

    @TypeConverter
    fun intListToString(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun stringToIntList(value: String?): List<Int>? {
        return value?.let {string ->
            string.split(",").filter { it != "" }.map { it.toInt() }
        }
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }?.filter { it != "" }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return if (location == null) null else "${location.latitude},${location.longitude}"
    }

    @TypeConverter
    fun toLocation(locationString: String?): Location? {
        if (locationString == null) return null

        val parts = locationString.split(",")
        if (parts.size != 2) return null

        val latitude = parts[0].toDoubleOrNull() ?: return null
        val longitude = parts[1].toDoubleOrNull() ?: return null

        val location = Location("Generated") // Provider is not important for storagelocation.latitude = latitude
        location.longitude = longitude
        return location
    }
}


