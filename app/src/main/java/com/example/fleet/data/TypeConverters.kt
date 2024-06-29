package com.example.fleet.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.TypeConverter
import com.example.fleet.domain.Enums.Icono
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

}


