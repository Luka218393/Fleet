package com.example.fleet

import androidx.lifecycle.ViewModelProvider
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.cards
import com.example.fleet.data.chatBars
import com.example.fleet.data.settingState1
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.MainViewModel
import com.example.fleet.domain.viewModels.MainViewModelFactory
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.activities.ChatActivity
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.activities.SettingsActivity

class AppInstances (
    notificationViewModel: NotificationViewModel,
    /*Todo add remaining activities*/

){
    private val navigation = Navigation(this)
    val notificationActivity = NotificationActivity(cards = cards, viewModel = notificationViewModel, navigation = navigation)
    val settingsActivity = SettingsActivity(settingState1, navigation = navigation)
    val chatActivity = ChatActivity(chatBars = chatBars, navigation = navigation)
}