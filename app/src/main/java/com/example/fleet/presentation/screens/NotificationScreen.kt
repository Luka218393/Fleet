package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.example.fleet.FleetApplication
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.components.DateSeparator
import com.example.fleet.presentation.components.cards.card_dialogs.NotificationDialog
import com.example.fleet.presentation.components.cards.card_dialogs.PollDialog
import com.example.fleet.presentation.components.cards.card_dialogs.TaskDialog
import com.example.fleet.presentation.components.scaffold_elements.FloatingButton
import com.example.fleet.presentation.components.scaffold_elements.FloatingButtonState


class NotificationScreen(
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val viewModel: NotificationViewModel = ViewModelProvider(FleetApplication.viewModelStore, NotificationViewModelFactory())[NotificationViewModel::class.java]
): Screen{

    private val tag = "NotificationScreen"

    override val key: ScreenKey
        get() = Screens.NOTIFICATION.key

    @Composable
    override fun Content() {
        //
        val cards = viewModel.cards.collectAsState().value
        val lazyState = rememberLazyListState()

        Scaffold(
            floatingActionButton = {
                FloatingButton(
                    remember {
                        FloatingButtonState(
                            toggleNotificationDialog = { viewModel.toggleNotificationDialog() },
                            toggleTaskDialog =  { viewModel.toggleTaskDialog() } ,
                            togglePollDialog =  { viewModel.togglePollDialog() }
                        )
                    }
                )
            },
        ) { padding ->

            if (cards.isEmpty()){
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "No notifications yet",
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
            else {
                //
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = lazyState
                ) {
                    items(cards.size, key = { cards[it].id }) { index ->

                        cards[index].Create()

                        if (index + 1 < cards.size && cards[index + 1].createdAt.dayOfYear != cards[index].createdAt.dayOfYear || index == cards.size - 1) {
                            DateSeparator(date = cards[index].createdAt)
                        }
                    }
                }
            }
            //Todo make them scroll to the last when notificaions are created
            if (viewModel.isNotificationDialogShown){ NotificationDialog( onDismiss = {viewModel.toggleNotificationDialog()}, onConfirm = { a,b -> viewModel.createNotification(a,b);viewModel.toggleNotificationDialog() }) }
            if (viewModel.isPollDialogShown){ PollDialog(onDismiss = {viewModel.togglePollDialog()}, onConfirm = { a, b, c-> viewModel.createPoll(a,b,c); viewModel.togglePollDialog()}) }
            if (viewModel.isTaskDialogShown){ TaskDialog(onDismiss = {viewModel.toggleTaskDialog()}, onConfirm = { a, b -> viewModel.createTask(a,b); viewModel.toggleTaskDialog()}) }
        }
        }
    }



