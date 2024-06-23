package com.example.fleet

import com.example.fleet.data.settingState1
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.DialogueViewModel
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.presentation.screens.ChatScreen
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.screens.SettingsScreen
import com.example.fleet.presentation.screens.DialogueScreen


//Todo merge this somehow with Navigaton and make it viewModel
class AppInstances (
    notificationViewModel: NotificationViewModel,
    private val dialogueViewModel: DialogueViewModel,
    private val chatViewModel: ChatViewModel
    /*Todo add remaining activities*/

){
    private val navigation = Navigation(this)
    val notificationActivity = NotificationScreen(viewModel = notificationViewModel, navigation = navigation)
    val settingsActivity = SettingsScreen( settingState1, navigation = navigation)
    val chatActivity = ChatScreen(viewModel = chatViewModel, navigation = navigation)
    fun dialogueScreen(chatId: Int): DialogueScreen{
        return DialogueScreen(viewModel = dialogueViewModel, navigation = navigation, chatViewModel.getChatById(chatId) )
    }
}