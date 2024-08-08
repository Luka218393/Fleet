package com.example.fleet.presentation.screens.chat

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.fleet.presentation.components.ChatBar
import com.example.fleet.presentation.components.scaffold_elements.NavigationBottomBar
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton
import kotlin.system.measureTimeMillis

//
class ChatSelectionScreen: Screen{
    private val TAG = "ChatSelectionScreen"
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]


    @Composable
    override fun Content() {
        val time = measureTimeMillis {

        val nav = LocalNavigator.current
        val chats = viewModel.chats.collectAsState().value

        Scaffold(
            bottomBar = { NavigationBottomBar() },
            floatingActionButton = { SimpleFloatingButton ({ Navigation.goTo(Screens.CHAT_CREATION, nav) }, Icons.Default.Add) }
        ) { padding ->

            if (chats.isEmpty()) {
                Box(
                    modifier = Modifier.padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No chats yet",
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //this could be faster
                    items(chats, key = { it.id }) { chat ->
                        ChatBar(
                            navigateToChatScreen = {
                                Navigation.goTo(Screens.CHAT, nav, chat.id)
                                viewModel.changeMessageCollectorJob(chat.id)
                            },
                            chat = chat,
                            viewModel.getMessageText(chat.lastMessageId)

                        )
                    }
                }
            }
        }
        }
        Log.i(TAG,"Execution time: $time ms")
    }
}



