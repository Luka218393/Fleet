package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Enums.ChatType
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.domain.Models.TenantChat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ChatViewModel (
    val db: FleetDatabase,
): ViewModel() {
    //
    private var _chats: MutableStateFlow<List<Chat>> = MutableStateFlow(mutableListOf())
    var chats = _chats.asStateFlow()
    private var _messages: MutableStateFlow<List<Message>> = MutableStateFlow(mutableListOf())
    var messages = _messages.asStateFlow()
    private var _tenants: MutableStateFlow<List<Tenant>> = MutableStateFlow(mutableListOf())
    var tenants = _tenants.asStateFlow()

    //
    init {
        Log.i("ChatViewModel", "ChatViewModel init")
        chatsCollector()
    }

    //
    fun createChat(tenantIds: List<Int>, isPrivate: Boolean, title: String? = null) {
        val chatId = Random.nextInt(Int.MAX_VALUE)
        //Todo check if private chat already exists -> send message
        runBlocking {
            db.chatDao().upsert(
                Chat(
                    title = title
                        ?: FleetApplication.fleetModule.getTenantNameAndSurname(tenantIds.first())
                        ?: "Error",
                    chatType = ChatType.TENANT_TO_TENANT,
                    isPrivate = isPrivate,
                    id = chatId
                )
            )

            for (i in tenantIds + FleetApplication.fleetModule.tenantId) {
                db.tenantChatDao().upsert(
                    TenantChat(
                        tenantId = i,
                        chatId = chatId,
                        id = "$i,$chatId"
                    )
                )
            }
        }
    }


    //
    private fun chatsCollector() {
        viewModelScope.launch {
            db.tenantChatDao()
                .getChatsOfATenant(FleetApplication.fleetModule.tenantId).collect {
                _chats.value = it
            }
        }
    }

    //
    fun insertTenantsForChatCreation(isPersonal: Boolean) {
        runBlocking {
            withContext(Dispatchers.IO) {
                if(!isPersonal) {
                    _tenants.value = db.tenantDao()
                        .getTenantsByBuildingId(FleetApplication.fleetModule.buildingId)
                        .filterNot { it.id == FleetApplication.fleetModule.tenantId }
                }
                else{
                    _tenants.value = db.tenantChatDao().getTenantsForNewPersonalChat(FleetApplication.fleetModule.tenantId)
                }
            }
        }
    }


    //
    private var messageCollectorJob: Job? = null
    fun changeMessageCollectorJob(chatId: Int) {
        messageCollectorJob?.cancel()
        messageCollectorJob = viewModelScope.launch {
                db.messageDao().getByChatId(chatId).collect{ messages ->
                    _messages.update { messages }
            }
        }
    }

    //
    fun sendMessage(text: String, chatId: Int){
        runBlocking {
            db.messageDao().upsert(
                Message(
                    chatId = chatId,
                    senderId = FleetApplication.fleetModule.tenantId,
                    text = text,
                )
            )
        }
    }

    //
    fun getLastMessage(chatId: Int): String = runBlocking { db.messageDao().getLastMessageFromChat(chatId) ?: "No messages yet" }

    fun getChat(id: Int): Chat = runBlocking { db.chatDao().getById(id).first() }

    fun scrollToLastMessage(lazyState: LazyListState){
        //Make it scroll to the bottom not top of the last item
        viewModelScope.launch { lazyState.scrollToItem(0) }
    }
}

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

