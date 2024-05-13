package com.example.fleet.data.Models

data class Chat (
    val id: Int,
    var tenants: List<Int>,
    var messages: List<Message> = emptyList(),
    var title: String? = null,
    var profileImageResId: Int? = null,
    var isPrivate: Boolean = true,
    var isDiscussion: Boolean = false,
)