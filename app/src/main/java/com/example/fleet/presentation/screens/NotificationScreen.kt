package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.fragments.DateSeparator
import com.example.fleet.presentation.fragments.card_dialogs.NotificationDialog
import com.example.fleet.presentation.fragments.card_dialogs.PollDialog


class NotificationScreen(
    private val modifier: Modifier = Modifier,
    private val viewModel: NotificationViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, NotificationViewModelFactory())[NotificationViewModel::class.java]
)
    : BaseScreen(floatingButton = listOf( {viewModel.toggleNotificationDialog()}, { viewModel.toggleTaskDialog() }, { viewModel.togglePollDialog()}))
{

    @Composable
    override fun InnerContent() {

        val cards = viewModel.cards.collectAsState(emptyList()).value.filterNotNull()//Todo remove this filter not null

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(cards.size, key = { cards[it].id }) { index ->
                cards[index].Create()

                if (index + 1 < cards.size){
                    if (cards[index + 1].createdAt.day != cards[index].createdAt.day){
                        DateSeparator(date = cards[index + 1 ].createdAt)
                    }
                }

            }
        }

        if (viewModel.isNotificationDialogShown){
            NotificationDialog(
                onDismiss = {viewModel.toggleNotificationDialog()},
                onConfirm = {
                    a,b -> viewModel.createNotification(a,b)
                    viewModel.toggleNotificationDialog()
                }
            )
        }

        if (viewModel.isPollDialogShown){ PollDialog(onDismiss = {viewModel.togglePollDialog()}, onConfirm = { a, b -> viewModel.createPoll(a,b); viewModel.togglePollDialog()})
        }
        //Todo ad remaining creation dialogs
    }
}
