package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.R
import com.example.fleet.data.daos.notificationCards
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.presentation.ui.elements.BaseCard
import com.example.fleet.presentation.ui.elements.BottomBar
import com.example.fleet.presentation.ui.elements.ImageNotificationCard
import com.example.fleet.presentation.ui.elements.PollCard
import com.example.fleet.presentation.ui.elements.SimpleNotificationCard



class NotificationScreen(
    private val modifier: Modifier = Modifier,
    private val cards: List<BaseCard> = notificationCards)
    : BaseScreen()
{
    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            items(cards.size) { index ->
                cards[index].Create()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    NotificationScreen().Create(bottomBar = {BottomBar(modifier = Modifier)})
}