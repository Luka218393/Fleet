package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.domain.Models.TenantChat
import com.example.fleet.presentation.fragments.MessageBox
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

    fun getMessages(chatId: Int): List<MessageBox>{
        var a : List<MessageBox>
        runBlocking {
            a = messages.first().filter { it.chatId == chatId }.map{message -> MessageBox(message = message) }
        }
        return a
    }

    suspend fun getTenants(chat: Chat):List<Tenant>{
        val tenantChats = this.tenantChat.first().filter { it.chatId == chat.id }.map{it.tenantId}
        return this.tenants.first().filter{it.id in tenantChats}
    }

    fun getSettingsTenantId(): Int{
        return settings.value.tenantId
    }

}

@Suppress("UNCHECKED_CAST")
class DialogueViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DialogueViewModel::class.java)) {
            return DialogueViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

