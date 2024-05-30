package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.presentation.ui.fragments.BaseCard
import com.example.fleet.presentation.ui.fragments.BottomBar


class NotificationActivity(
    private val modifier: Modifier = Modifier,
    private val cards: List<BaseCard> = com.example.fleet.data.cards,
)
    : BaseActivity()
{
    @Composable
    override fun InnerContent() {
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
