package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.domain.Models.TenantChat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DialogueViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    private var messages: Flow<List<Message>>
    private var tenants: Flow<List<Tenant>>
    private var tenantChat: Flow<List<TenantChat>>
    init {
        runBlocking {//Todo make this smarter
            tenantChat = db.tenantChatDao().getAll()
            messages = db.messageDao().getAll()
            tenants = db.tenantDao().getAll()
        }
    }

    suspend fun getMessages(chat: Chat):List<Message>{
        return this.messages.first().filter { it.chatId == chat.id }
    }

    suspend fun getTenants(chat: Chat):List<Tenant>{
        val tenantChats = this.tenantChat.first().filter { it.chatId == chat.id }.map{it.tenantId}
        return this.tenants.first().filter{it.id in tenantChats}
    }

}

@Suppress("UNCHECKED_CAST")
class DialogueViewModelFactory(private val db: FleetDatabase, private val settings: MutableStateFlow<Settings>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DialogueViewModel::class.java)) {
            return DialogueViewModel(db, settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

