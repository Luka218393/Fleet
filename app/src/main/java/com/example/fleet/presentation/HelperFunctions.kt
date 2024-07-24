package com.example.fleet.presentation

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

object HelperFunctions {
    private fun formatDate(date: Date): List<String>{
        return SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(date).split("/")
    }

    fun cutString(text: String, dots: Boolean = true, length: Int = 20): String{
        return if (text.length > length) text.slice(0..length) + " ..." else text
    }

    fun getDayAndMonth(date: LocalDateTime): String{
        return "Todo FIX ME HELPER FUNCTIONS MIN AND HOUR"
    }
    fun getMinAndHour(date: LocalDateTime):String{
        return "Todo FIX ME HELPER FUNCTIONS MIN AND HOUR"
    }
}