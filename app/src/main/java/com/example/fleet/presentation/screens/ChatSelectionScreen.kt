package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.Select_ChatBar
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.SimpleFloatingButton


class ChatSelectionScreen: Screen{
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]

    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        val chats = viewModel.chats.collectAsState(emptyList()).value

        Scaffold(
            bottomBar = { BottomBar() },
            floatingActionButton = { SimpleFloatingButton ({ Navigation.goTo(Screens.CHAT_CREATION, nav) }, Icons.Default.Add) }
        ) { padding ->


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(chats, key = {it.id}) { chat ->
                        Select_ChatBar(
                            navigateToChatScreen = {
                                Navigation.goTo( Screens.CHAT, nav, chat.id)
                                viewModel.changeMessageCollectorJob(chat.id)
                            }, chat = chat,
                            lastMessageText = viewModel.getLastMessage(chat.id)
                        )
                    }
                }
            }
        }
    }



