package com.example.fleet.domain.Models

import com.example.fleet.domain.Enums.ChatType

data class Chat (
    val id: Int,
    var tenants: List<Int>,
    var messages: List<Message> = emptyList(),
    var title: String? = null,
    var profileImageResId: Int? = null,
    var isPrivate: Boolean = true,
    var chatType: ChatType,
)