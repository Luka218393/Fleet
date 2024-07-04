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
import com.example.fleet.domain.Models.Tenant
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

    private var _chats: MutableStateFlow<List<Chat>> = MutableStateFlow(mutableListOf())
    var chats = _chats.asStateFlow()
    private var _messages: MutableStateFlow<List<Message>> = MutableStateFlow(mutableListOf())
    var messages = _messages.asStateFlow()
    private var _tenants: MutableStateFlow<List<Tenant>> = MutableStateFlow(mutableListOf())
    var tenants = _tenants.asStateFlow()

    init{
        runBlocking{
            Log.i("ChatViewModel","ChatViewModel init")
            insertTenantsChats()
            insertAllTenants()
        }
    }
    private fun insertTenantsChats(){
        viewModelScope.launch {
            db.tenantChatDao().getByTenantId(settings.value.tenantId).collect { tenantChats ->
                _chats.value = db.chatDao().getChatsByIds(tenantChats.map { it.chatId }).first()
            }
        }
    }
    private fun insertAllTenants(){
        Log.i("ChatViewModel","Insertion of all Tenants")
        viewModelScope.launch {
            db.tenantDao().getTenantsByBuildingId(settings.value.buildingId).collect { tenants ->
                _tenants.value = tenants
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

    fun getLastMessage(chatId: Int): MutableState<String> {
        val a = mutableStateOf("No message yet")
        viewModelScope.launch {
            db.messageDao().getLastMessagefromChat(chatId).collect{a.value = it?.text ?: "aaa" }//Todo this crashes the app {it} can be null
        }
        return a
    }

    fun getTenantId(): Int = settings.value.tenantId

    fun getChat(id: Int): Chat = runBlocking { db.chatDao().getById(id).first() }

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

