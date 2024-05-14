package com.example.fleet.domain.Models

import java.util.Date

data class Message (
    val id: Int,
    var senderId: Int,
    var hash: String? = null,
    var sendingTime: Date,
    var profileImage: Int? = null,
    var imageUrl: String? = null,
)