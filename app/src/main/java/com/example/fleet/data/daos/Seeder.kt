package com.example.fleet.data.daos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import com.example.fleet.R
import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.ui.elements.BaseCard
import com.example.fleet.presentation.ui.elements.NotificationCard
import com.example.fleet.presentation.ui.elements.PollCard

data class Tenants(

    val tenant1: Tenant = Tenant(
        id = 1,
        name = "Tenant1",
        apartmentId = 1,
    ),
    val tenant2: Tenant = Tenant(
        id = 3,
        name = "Tenant3",
        apartmentId = 2,
    ),
    val tenant3: Tenant = Tenant(
        id = 2,
        name = "Tenant2",
        apartmentId = 1,
    ),
    )
private var notifications: List<Notification> = listOf(
    Notification(
        id = 1,
        title = "Notification 1",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.Favorite,
        creatorId = 1,
        imageResId = R.drawable.lukinaikona
    ),

    Notification(
        id = 2,
        title = "Notification 2",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.List,
        creatorId = 1,
    ),
    Notification(
        id = 3,
        title = "Notification 3",
        text = "My name is bearling Lino, i eat something nice everyday, is this what life is having expectations that i+ve built foe myself. i am letting loose, loooose, driftingall away, gonna end u somewhere anywhere letting loose",
        iconResId = Icons.Default.Face,
        creatorId = 2,
        imageResId = R.drawable.flagicon
    )
)

private var pollOptions: List<PollOption> = listOf(
    PollOption(
        id = 1,
        value = "Option 1",
        votes = 100,
        pollId = 1
    ),
    PollOption(
        id = 2,
        value = "Option 2",
        votes = 10,
        pollId = 1
    ),
    PollOption(
        id = 3,
        value = "Option 3",
        votes = 1,
        pollId = 1
    ),
    PollOption(
        id = 4,
        value = "Option 4",
        votes = 12,
        pollId = 1
    ),
    PollOption(
        id = 5,
        value = "Option 1",
        votes = 32,
        pollId = 1
    ),
    PollOption(
        id = 6,
        value = "Da",
        votes = 12,
        pollId = 2
    ),
    PollOption(
        id = 7,
        value = "Ne",
        votes = 123,
        pollId = 2
    ),
)

private var polls: List<Poll> = listOf(
    Poll(
        id = 1,
        creatorId = 1,
        title = "First poll ever",
        question = "Why is sky blue?",
        pollType = PollType.MULTIPLE_CHOICE,
    ),
    Poll(
        id = 2,
        creatorId = 1,
        title = "Second poll",
        question = "Is this poll better than the first one?",
        pollType = PollType.SINGLE_CHOICE,
    ),
    Poll(
        id = 3,
        creatorId = 2,
        title = "Hidden poll",
        question = "You cant see me",
        pollType = PollType.SINGLE_CHOICE,
    ),

)

var pollCards: List<PollCard> = polls.map { poll -> PollCard(poll, pollOptions.filter { it.pollId == poll.id })}

public var cards: List<BaseCard> = pollCards + notifications.map { notification -> NotificationCard(notification) }
