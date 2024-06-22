package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.DialogueViewModel
import com.example.fleet.presentation.fragments.TopBar


//Todo come up with reasonable name for this
class DialogueScreen (
    private val viewModel: DialogueViewModel,
    private val navigation: Navigation,
    private val chat: Chat
) : Screen{
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { TopBar(Modifier, chat.title!!) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                .padding(padding)
                .fillMaxSize()
            ) {
                items(viewModel.getMessages(chat.id)){
                    message -> message.Create(tenantId = viewModel.getSettingsTenantId())
                }
            }
        }
    }
}

