package com.example.fleet.domain

import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.AppInstances
import com.example.fleet.domain.Enums.Screens


class Navigation(private val appInstances: AppInstances)
{
    fun goTo(screen: Screens, navigator: Navigator?){
        // TODO make so that you cant push activity that you are alredy on
        when(screen){
            Screens.CHAT_SCREEN -> navigator?.push(appInstances.chatActivity)
            Screens.SETTINGS_SCREEN -> navigator?.push(appInstances.settingsActivity)
            Screens.NOTIFICATION_SCREEN -> navigator?.push(appInstances.notificationActivity)
        }
    }
}




//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
