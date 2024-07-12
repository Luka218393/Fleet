package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class ChatViewModel (
    val db: FleetDatabase,
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
            insertChats()
            insertAllTenants()
        }
    }

    fun createChat(tenantIds: List<Int>, isPrivate: Boolean, title: String){
        val chatId = Random.nextInt(999999999)
        runBlocking {
            db.chatDao().upsert(
                Chat(
                    title = title,
                    chatType = ChatType.TENANT_TO_TENANT,
                    isPrivate = isPrivate,
                    id = chatId
                )
            )
            for (i in tenantIds) {
                db.tenantChatDao().upsert(
                    TenantChat(
                        tenantId = i,
                        chatId = chatId,
                        id = "$i,$chatId"
                    )
                )
            }
            db.tenantChatDao().upsert(
                TenantChat(
                    tenantId = FleetApplication.fleetModule.settings.value.tenantId,
                    chatId = chatId,
                    id = "${FleetApplication.fleetModule.settings.value.tenantId},$chatId"
                )
            )
        }
    }
    private fun insertChats(){
        viewModelScope.launch {
            db.tenantChatDao().getByTenantId(FleetApplication.fleetModule.settings.value.tenantId).collect { tenantChats ->
                _chats.value = db.chatDao().getChatsByIds(tenantChats.map { it.chatId }).first()
            }
        }
    }
    private fun insertAllTenants(){
        viewModelScope.launch {
            db.tenantDao().getTenantsByBuildingId(FleetApplication.fleetModule.settings.value.buildingId).collect { tenants ->
                _tenants.value = tenants.filterNot{it.id == FleetApplication.fleetModule.settings.value.tenantId}
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
                    senderId = FleetApplication.fleetModule.settings.value.tenantId,
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

    fun getTenantId(): Int = FleetApplication.fleetModule.settings.value.tenantId

    fun getChat(id: Int): Chat = runBlocking { db.chatDao().getById(id).first() }

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

