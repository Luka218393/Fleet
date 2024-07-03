package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.CreateMessageBox
import com.example.fleet.presentation.fragments.DateSeparator


class ChatScreen(
    @Transient
    private val chatId: Int,
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java],
    @Transient
    private val chat: Chat = viewModel.getChat(chatId)
) : BaseScreen(
    topBar = chat.title,
    inputBottomBar = {str -> viewModel.sendMessage(str, chatId)}
){


    @Composable
    override fun InnerContent() {
        val messages = viewModel.messages.collectAsState(emptyList()).value


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                reverseLayout = true
            ) {
                //Todo make so on new message you scroll to the bottom of the chat
                items(messages.size, key = { messages[it].id }) { index ->
                    CreateMessageBox(message = messages[index], tenantId = viewModel.getTenantId() )
                    if (index + 1 < messages.size){
                        if (messages[index + 1].sendingTime.day != messages[index].sendingTime.day){
                            DateSeparator(date = messages[index].sendingTime)
                        }
                    }
                }
            }
        }

}

