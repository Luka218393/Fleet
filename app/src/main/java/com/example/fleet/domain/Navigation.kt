package com.example.fleet.domain

import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.cards
import com.example.fleet.data.chatBars
import com.example.fleet.data.settingState1
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.presentation.activities.ChatActivity
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.activities.SettingsActivity

class Navigation (

){
    fun goTo(screen: Screens, navigator: Navigator?){
        // TODO make so that you cant push activity that you are alredy on
        when(screen){
            Screens.CHAT_SCREEN -> navigator?.push(ChatActivity(chatBars = chatBars))
            Screens.SETTINGS_SCREEN -> navigator?.push(SettingsActivity(settingState1))
            Screens.NOTIFICATION_SCREEN -> navigator?.push(NotificationActivity(cards = cards))
        }
    }
}




//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
