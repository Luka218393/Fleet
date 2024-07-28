package com.example.fleet.domain

import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.presentation.screens.chat.ChatCreationScreen
import com.example.fleet.presentation.screens.chat.ChatScreen
import com.example.fleet.presentation.screens.chat.ChatSelectionScreen
import com.example.fleet.presentation.screens.settings.DisplayScreen
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.screens.settings.SettingsScreen
import com.example.fleet.presentation.screens.WorkInProgressScreen
import com.example.fleet.presentation.screens.creation.AddressSelectionScreen
import com.example.fleet.presentation.screens.creation.LogInScreen
import com.example.fleet.presentation.screens.creation.SignInScreen


object Navigation{
    fun goTo(screen: Screens, navigator: Navigator?, componentId: String = "1"){
        // TODO make so that you cant push activity that you are already on
        when(screen){
            Screens.CHAT_SELECTION -> navigator?.push(ChatSelectionScreen())
            Screens.SETTINGS -> navigator?.push(SettingsScreen())
            Screens.NOTIFICATION -> navigator?.push(NotificationScreen())
            Screens.CHAT -> navigator?.push(ChatScreen(chatId = componentId ))
            Screens.DISPLAY -> navigator?.push(DisplayScreen(tenantId = componentId))
            Screens.CHAT_CREATION -> navigator?.push((ChatCreationScreen()))
            Screens.WORK_IN_PROGRESS -> navigator?.push(WorkInProgressScreen())

            Screens.ADDRESS_SELECTION -> navigator?.push(AddressSelectionScreen())
            Screens.SIGN_IN -> navigator?.push(SignInScreen())
            Screens.LOG_IN -> navigator?.push(LogInScreen())

        }
    }

    fun pop(navigator: Navigator?){
        navigator?.pop()
    }
}




//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
