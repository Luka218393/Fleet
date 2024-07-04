package com.example.fleet.domain

import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.presentation.screens.ChatCreationScreen
import com.example.fleet.presentation.screens.ChatScreen
import com.example.fleet.presentation.screens.ChatSelectionScreen
import com.example.fleet.presentation.screens.DisplayScreen
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.screens.SettingsScreen


object Navigation{
    fun goTo(screen: Screens, navigator: Navigator?, chatId: Int = 1){
        // TODO make so that you cant push activity that you are already on
        when(screen){
            Screens.CHAT_SELECTION -> navigator?.push(ChatSelectionScreen())
            Screens.SETTINGS -> navigator?.push(SettingsScreen())
            Screens.NOTIFICATION -> navigator?.push(NotificationScreen())
            Screens.CHAT -> navigator?.push(ChatScreen(chatId = chatId ))
            Screens.DISPLAY -> navigator?.push(DisplayScreen())
            Screens.CHAT_CREATION -> navigator?.push((ChatCreationScreen()))
        }
    }

    fun pop(navigator: Navigator?){
        navigator?.pop()
    }
}




//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
