package com.example.fleet.data.daos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import com.example.fleet.R
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.ui.elements.BaseCard
import com.example.fleet.presentation.ui.elements.ImageNotificationCard
import com.example.fleet.presentation.ui.elements.SimpleNotificationCard

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

public var notificationCards: List<BaseCard> = notifications.map { notification -> ImageNotificationCard(notification) }
