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
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory


class ChatSelectionScreen: BaseScreen(){
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]

    @Composable
    override fun InnerContent() {
        val nav = LocalNavigator.current
        val chatBars = viewModel.chatBars.collectAsState(emptyList()).value

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(chatBars) { chatBar ->
                chatBar.Create(navigateToDialogueScreen = {
                    Navigation.goTo( Screens.CHAT, nav, chatBar.chat.id)
                    viewModel.changeMessageCollectorJob(chatBar.chat.id)
                } )
            }
        }
    }
}
