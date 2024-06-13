package com.example.fleet

import com.example.fleet.data.chatBars
import com.example.fleet.data.settingState1
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.DialogueViewModel
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.presentation.activities.ChatActivity
import com.example.fleet.presentation.activities.DialogueActivity
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.activities.SettingsActivity

class AppInstances (
    notificationViewModel: NotificationViewModel,
    dialogueViewModel: DialogueViewModel
    /*Todo add remaining activities*/

){
    private val navigation = Navigation(this)
    val notificationActivity = NotificationActivity(viewModel = notificationViewModel, navigation = navigation)
    val settingsActivity = SettingsActivity(settingState1, navigation = navigation)
    val chatActivity = ChatActivity(chatBars = chatBars, navigation = navigation)
    val dialogueActivity = DialogueActivity(dialogueViewModel, navigation = navigation)
}