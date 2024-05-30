package com.example.fleet.data

import androidx.room.TypeConverter
import java.time.LocalDate
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
}