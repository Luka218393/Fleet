package com.example.fleet.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import com.example.fleet.R
import com.example.fleet.domain.Enums.ChatType
import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Models.Apartment
import com.example.fleet.domain.Models.Building
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.SettingsState
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.fragments.BaseCard
import com.example.fleet.presentation.fragments.ChatBar
import com.example.fleet.presentation.fragments.NotificationCard
import com.example.fleet.presentation.fragments.PollCard

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
 var notifications: List<Notification> = listOf(
    Notification(
        id = 1,
        title = "Notification 1",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.Favorite,
        creatorId = 1,
        imageResId = R.drawable.lukinaikona,
        buildingId = 1
    ),

    Notification(
        id = 2,
        title = "Notification 2",
        text = "Lorem ipsum dolor sit amet",
        iconResId = Icons.Default.List,
        creatorId = 1,
        buildingId = 1
    ),
    Notification(
        id = 3,
        title = "Notification 3",
        text = "My name is bearling Lino, i eat something nice everyday, is this what life is having expectations that i+ve built foe myself. i am letting loose, loooose, driftingall away, gonna end u somewhere anywhere letting loose",
        iconResId = Icons.Default.Face,
        creatorId = 2,
        imageResId = R.drawable.flagicon,
        buildingId = 1
    )
)
var pollOptions: List<PollOption> = listOf(
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
var polls: List<Poll> = listOf(
    Poll(
        id = 1,
        creatorId = 1,
        title = "First poll ever",
        question = "Why is sky blue?",
        pollType = PollType.MULTIPLE_CHOICE,
        buildingId = 1
    ),
    Poll(
        id = 2,
        creatorId = 1,
        title = "Second poll",
        question = "Is this poll better than the first one?",
        pollType = PollType.SINGLE_CHOICE,
        buildingId = 1
    ),
    Poll(
        id = 3,
        creatorId = 2,
        title = "Hidden poll",
        question = "You cant see me",
        pollType = PollType.SINGLE_CHOICE,
        buildingId = 1
    ),

)
var pollCards: List<PollCard> = polls.map { poll -> PollCard(poll, pollOptions.filter { it.pollId == poll.id }) }

val chats: List<Chat> = listOf(
    Chat(
        id = 1,
        title = "Luka",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.TENANT_TO_TENANT
    ),
    Chat(
        id = 2,
        title = "Nina",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.TENANT_TO_TENANT
    ),
    Chat(
        id = 3,
        title = "Buy new plants to decorate building",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.DISCUSSION
    ),
    Chat(
        id = 4,
        title = "All tenants",
        profileImageResId = R.drawable.lukinaikona,
        chatType = ChatType.EVERYONE
    )
)
val chatBars = chats.map { chat -> ChatBar(chat) }

var buildings = listOf(
    Building(
        id = 1,
        address = "1"
    ),
    Building(
        id = 2,
        address = "2"
    ),
    Building(
        id = 3,
        address = "3"
    ),
)

var apartments = listOf(
    Apartment(
        id = 1,
        buildingId = 1
    ),
    Apartment(
        id = 2,
        buildingId = 2
    ),
    Apartment(
        id = 3,
        buildingId = 1
    )

)

val settingState1 = SettingsState(
    id = 1,
    tenant = Tenants().tenant1,
    apartment = apartments[0],
    building = buildings[0]

)

val settings1 = Settings(
    id = 1,
    tenantId = 1,
    apartmentId = 1,
    buildingId = 1

)
var cards: List<BaseCard> = pollCards + notifications.map { notification -> NotificationCard(notification) }
