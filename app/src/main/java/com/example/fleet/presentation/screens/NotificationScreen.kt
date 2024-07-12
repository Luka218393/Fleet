package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.fragments.DateSeparator
import com.example.fleet.presentation.fragments.cards.card_dialogs.NotificationDialog
import com.example.fleet.presentation.fragments.cards.card_dialogs.PollDialog
import com.example.fleet.presentation.fragments.cards.card_dialogs.TaskDialog
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.FloatingButton


class NotificationScreen(
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val viewModel: NotificationViewModel = ViewModelProvider(FleetApplication.viewModelStore, NotificationViewModelFactory())[NotificationViewModel::class.java]
)
    : Screen{

    @Composable
    override fun Content() {

        val cards = viewModel.cards.collectAsState(emptyList()).value.filterNotNull()//Todo remove this filter not null

        Scaffold(
            bottomBar = {BottomBar()},
            floatingActionButton = {
                    FloatingButton(
                        toggleNotificationDialog = { viewModel.toggleNotificationDialog() },
                        toggleTaskDialog =  { viewModel.toggleTaskDialog() } ,
                        togglePollDialog =  { viewModel.togglePollDialog() }
                    )
                },
        ) { padding ->
                LazyColumn(
                    modifier = modifier.fillMaxSize().padding(padding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(cards.size, key = { cards[it].id }) { index ->

                        cards[index].Create()

                        if (index + 1 < cards.size){
                            if (cards[index + 1].createdAt.day != cards[index].createdAt.day){
                                DateSeparator(date = cards[index].createdAt)
                            }
                        }
                    }
                }

                if (viewModel.isNotificationDialogShown){ NotificationDialog( onDismiss = {viewModel.toggleNotificationDialog()}, onConfirm = { a,b -> viewModel.createNotification(a,b);viewModel.toggleNotificationDialog() }) }

                if (viewModel.isPollDialogShown){ PollDialog(onDismiss = {viewModel.togglePollDialog()}, onConfirm = { a, b -> viewModel.createPoll(a,b); viewModel.togglePollDialog()}) }

                if (viewModel.isTaskDialogShown){ TaskDialog(onDismiss = {viewModel.toggleTaskDialog()}, onConfirm = { a, b -> viewModel.createTask(a,b); viewModel.toggleTaskDialog()}) }
            }
        }
    }



