package com.example.fleet.domain.Models

import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Enums.Voters
import java.util.Date

data class Poll (
    val id: Int,
    val creator: Tenant,
    val dateCreated: Date,
    val title: String,
    val question: String,
    val options: List<PollOption>,
    val pollType: PollType,
    val voteEndDate: Date,
    val isPublic: Boolean,
    val voters: Voters,
)