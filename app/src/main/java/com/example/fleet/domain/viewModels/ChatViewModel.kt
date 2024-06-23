package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.TenantChat
import com.example.fleet.presentation.fragments.ChatBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ChatViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    private var tenantChats: Flow<List<TenantChat>>
    private var chats: List<Chat>
    var chatBars: List<ChatBar>

    init {
        runBlocking {//Todo make this smarter
            tenantChats = db.tenantChatDao().getByTenantId(settings.value.tenantId)
            chats = tenantChats.first().map { db.chatDao().getById(it.chatId).first() }
            chatBars = chats.map { ChatBar(it, getLastMessageText =  {"yeaaaaa"}, ) }
        }
    }

    fun getChatById(id : Int): Chat{
        val a: Chat
        runBlocking{
            a =  db.chatDao().getById(id).first()
        }
        return a
    }
}

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private val settings: MutableStateFlow<Settings>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(FleetApplication.fleetModule.fleetDatabase, settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

