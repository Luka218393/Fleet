package com.example.fleet.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.InputBottomBar
import com.example.fleet.presentation.fragments.TopBar


//Todo come up with reasonable name for this
class ChatScreen(
    private val chatId: Int
) : Screen {
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]
    private val chat = viewModel.getChat(chatId)
    @Composable
    override fun Content() {
        val messageBoxes = viewModel.messageBoxes.collectAsState(emptyList()).value
        Scaffold(
            topBar = { TopBar(Modifier, chat.title!!, cancelMessageCollection = {Log.i("ChatScreen","messageInsertionCancelled: ")} ) },
            bottomBar = {
                InputBottomBar(
                    modifier = Modifier,
                    send = {viewModel.sendMessage(it, chatId)}
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(messageBoxes){messageBox ->
                    messageBox.Create(tenantId = viewModel.getTenantId())
                }
            }
        }
    }
}

