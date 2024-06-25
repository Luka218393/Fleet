package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Settings
import com.example.fleet.presentation.fragments.ChatBar
import com.example.fleet.presentation.fragments.MessageBox
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChatViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    private var _chatBars: MutableStateFlow<List<ChatBar>> = MutableStateFlow(mutableListOf())
    var chatBars = _chatBars.asStateFlow()
    private var _messageBoxes: MutableStateFlow<List<MessageBox>> = MutableStateFlow(mutableListOf())
    var messageBoxes = _messageBoxes.asStateFlow()

    init{
        runBlocking{
            Log.i("ChatViewModel","ChatViewModel init")
            insertChatBars()
        }
    }
    private fun insertChatBars(){
        viewModelScope.launch {
            db.chatDao().getAll().collect{chats->
                _chatBars.update { chats.map{chat -> ChatBar(chat, getLastMessage(chat.id).text)} }
            }
        }
    }

    private var messageCollectorJob: Job? = null
    fun changeMessageCollectorJob(chatId: Int) {
        messageCollectorJob?.cancel()
        messageCollectorJob = viewModelScope.launch {
                db.messageDao().getByChatId(chatId).collect{ messages ->
                    _messageBoxes.update { messages.map{ MessageBox(it) } }
            }
        }
    }

    fun sendMessage(text: String, chatId: Int){
        viewModelScope.launch {
            db.messageDao().upsert(
                Message(
                    chatId = chatId,
                    senderId = settings.value.tenantId,
                    text = text,
                )
            )
        }
    }

    private fun getLastMessage(chatId: Int):Message{
        var a : Message?
        runBlocking {
            a = db.messageDao().getLastMessagefromChat(chatId).first()
        }
        return a ?: Message(text = "No messages yet", chatId = 1, senderId = 1)
    }

    fun getTenantId(): Int = settings.value.tenantId

    fun getChat(id: Int): Chat {
        var a : Chat
        runBlocking { a = db.chatDao().getById(id).first() }
        return a
    }
}

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

