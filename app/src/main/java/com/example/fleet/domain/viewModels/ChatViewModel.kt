package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Settings
import com.example.fleet.presentation.fragments.ChatBar
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
    private var _messages: MutableStateFlow<List<Message>> = MutableStateFlow(mutableListOf())
    var messages = _messages.asStateFlow()

    init{
        runBlocking{
            Log.i("ChatViewModel","ChatViewModel init")
            insertChatBars(settings.value.tenantId)
        }
    }

    private fun getTenantById(id: Int): String {
        val a: String
        runBlocking { a = db.tenantDao().getById(id).first().name }
        return a
    }
    private fun insertChatBars(tenantId: Int){
        var chats: List<Chat> = emptyList()
        fun update(){
            _chatBars.update { chats.map{chat -> ChatBar(chat, getLastMessage(chat.id)) } }
        }
        viewModelScope.launch {
            db.tenantChatDao().getByTenantId(tenantId).collect{
                tenantChats ->
                chats = db.chatDao().getChatsByIds(tenantChats.map{it.chatId}).first()
                update()

            }
        }
    }

    private var messageCollectorJob: Job? = null
    fun changeMessageCollectorJob(chatId: Int) {
        messageCollectorJob?.cancel()
        messageCollectorJob = viewModelScope.launch {
                db.messageDao().getByChatId(chatId).collect{ messages ->
                    _messages.update { messages }
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

    private fun getLastMessage(chatId: Int): MutableState<String> {
        val a = mutableStateOf("No message yet")
        viewModelScope.launch {
            db.messageDao().getLastMessagefromChat(chatId).collect{a.value = it?.text ?: "aaa" }//Todo this crashes the app {it} can be null
        }
        return a
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

