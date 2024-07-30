package com.example.fleet.presentation

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object HelperFunctions {

    fun cutString(text: String, dots: Boolean = true, length: Int = 20): String {
        return if (text.length > length) text.slice(0..length) + " ..." else text
    }

    fun getDayAndMonth(date: LocalDate): String = "${date.dayOfMonth} ${date.month}"

    fun getMinAndHour(date: LocalDateTime): String = "${String.format("%02d", date.hour)}:${String.format("%02d", date.minute)}"


    fun stringToLocalDate(
        day: String,
        month: String,
        year: String
    ): LocalDate { // Return nullable LocalDate to handle potential errors

        val formattedDay = String.format("%02d", day.toInt())
        val formattedMonth = String.format("%02d", month.toInt())
        val dateString = "$formattedDay-$formattedMonth-$year"
        Log.i("aaa",dateString)
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"))

    }
}