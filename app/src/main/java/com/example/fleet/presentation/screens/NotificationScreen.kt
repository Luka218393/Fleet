package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory


class NotificationScreen(
    private val modifier: Modifier = Modifier,
)
    : BaseScreen()
{
    private val viewModel: NotificationViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, NotificationViewModelFactory())[NotificationViewModel::class.java]

    @Composable
    override fun InnerContent() {
        var cards = viewModel.cards.collectAsState(emptyList()).value.sortedBy { it.createdAt }
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(cards, key = { it.id }) { card ->
                card.Create()
            }
        }
    }
}
