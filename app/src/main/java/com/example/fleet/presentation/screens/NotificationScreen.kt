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
import com.example.fleet.presentation.fragments.CreateNotificationDialog


class NotificationScreen(
    private val modifier: Modifier = Modifier,
    private val viewModel: NotificationViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, NotificationViewModelFactory())[NotificationViewModel::class.java]
)
    : BaseScreen(floatingButton = true, {viewModel.toggleNotificationDialog()},{ viewModel.toggleTaskDialog() },{ viewModel.togglePollDialog() }, )
{

    @Composable
    override fun InnerContent() {

        val cards = viewModel.cards.collectAsState(emptyList()).value.filterNotNull()//.sortedByDescending { it.createdAt }//Todo remove this filter not null

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(cards, key = { it.id }) { card ->
                card.Create()
            }
        }

        if (viewModel.isNotificationDialogShown){ CreateNotificationDialog(onDismiss = {viewModel.toggleNotificationDialog()}, onConfirm = {a,b -> viewModel.createNotification(a,b); viewModel.toggleNotificationDialog()})
        }
        ///Todo aad remaining creation dialogs
    }
}
