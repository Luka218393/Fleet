package com.example.fleet.domain

import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.settingState1
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.presentation.screens.ChatScreen
import com.example.fleet.presentation.screens.DialogueScreen
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.screens.SettingsScreen


object Navigation{
    fun goTo(screen: Screens, navigator: Navigator?, chatId: Int = 1){
        // TODO make so that you cant push activity that you are alredy on
        when(screen){
            Screens.CHAT_SCREEN -> navigator?.push(ChatScreen())
            Screens.SETTINGS_SCREEN -> navigator?.push(SettingsScreen( settingState1))
            Screens.NOTIFICATION_SCREEN -> navigator?.push(NotificationScreen())
            Screens.DIALOGUE_SCREEN -> navigator?.push(DialogueScreen(chatId = chatId ))
        }
    }

    fun pop(navigator: Navigator?){
        navigator?.pop()
    }
}




//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
