package com.example.fleet.data.Models

import java.util.Date

data class Message (
    val id: Int,
    var senderId: Int,
    var hash: String? = null,
    var sendingTime: Date,//Možda ne radi
    var profileImage: Int? = null,
    var imageUrl: String? = null,
)