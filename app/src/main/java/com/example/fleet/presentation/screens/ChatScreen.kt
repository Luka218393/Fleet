package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.CreateMessageBox
import com.example.fleet.presentation.fragments.DateSeparator
import com.example.fleet.presentation.fragments.scaffold_elements.InputBottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.TopBar


class ChatScreen(
    private val chatId: Int
) : Screen {
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]
    private val chat = viewModel.getChat(chatId)

    @Composable
    override fun Content() {
        val messages = viewModel.messages.collectAsState(emptyList()).value

        Scaffold(
            topBar = { TopBar(Modifier, chat.title!!) },
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
                    .fillMaxSize(),
                reverseLayout = true
            ) {
                //Todo make so on new message you scroll to the bottom of the chat
                items(messages.size, key = { messages[it].id }) { index ->
                    if (index-1 >= 0){
                        if (messages[index-1].sendingTime.day != messages[index].sendingTime.day){
                            DateSeparator(date = messages[index].sendingTime)
                        }
                    }
                    CreateMessageBox(message = messages[index], tenantId = viewModel.getTenantId() )


                }
            }
        }
    }
}

