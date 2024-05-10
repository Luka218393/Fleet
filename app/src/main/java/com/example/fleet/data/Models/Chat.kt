package com.example.fleet.data.Models

open class Chat (
    public val id: Int,
    public var tenantIds: List<Int>,
    public var messageIds: List<Int> = emptyList(),
    var title: String? = null
)