package com.example.fleet.presentation.screens.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.components.CreateMessageBox
import com.example.fleet.presentation.components.DateSeparator
import com.example.fleet.presentation.components.scaffold_elements.ChatTopBar
import com.example.fleet.presentation.components.scaffold_elements.InputBottomBar

//
class ChatScreen(
    @Transient
    private val chatId: String,
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java],
    @Transient
    private val chat: Chat = viewModel.getChat(chatId)
) : Screen{

    @Composable
    override fun Content() {
        val messages = viewModel.messages.collectAsState(emptyList()).value
        val lazyState = rememberLazyListState()
        val nav = LocalNavigator.current
        Scaffold(
            topBar = {
                ChatTopBar(title = chat.title,
                    onBack = { Navigation.pop(nav)},
                    onTextPress = {Navigation.goTo( Screens.DISPLAY, nav, viewModel.getTenantIdFromChat(chatId))} )},
            bottomBar = {
                InputBottomBar( modifier = Modifier,
                    send = {
                        viewModel.sendMessage(it, chat)
                        viewModel.scrollToLastMessage(lazyState)
                    } ) } ,
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                reverseLayout = true,
                state = lazyState
            ) {

                items(messages.size, key = { messages[it].id }) { index ->

                    CreateMessageBox(message = messages[index])

                    if (index + 1 < messages.size && messages[index + 1].sendingTime.dayOfYear != messages[index].sendingTime.dayOfYear || index == messages.size - 1) {
                        DateSeparator(date = messages[index].sendingTime)
                    }
                }
            }
        }
    }
}



