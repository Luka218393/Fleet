package com.example.fleet.domain.navigation

import android.util.Log
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.screens.WorkInProgressScreen
import com.example.fleet.presentation.screens.chat.ChatCreationScreen
import com.example.fleet.presentation.screens.chat.ChatScreen
import com.example.fleet.presentation.screens.chat.ChatSelectionScreen
import com.example.fleet.presentation.screens.creation.AddressSelectionScreen
import com.example.fleet.presentation.screens.creation.LogInScreen
import com.example.fleet.presentation.screens.creation.SignInScreen
import com.example.fleet.presentation.screens.settings.SettingsScreen
import com.example.fleet.presentation.screens.settings.display.DisplayApartment
import com.example.fleet.presentation.screens.settings.display.DisplayBuilding
import com.example.fleet.presentation.screens.settings.display.DisplayTenant

//Todo add secondary navigation to login screens
// Todo make display and chat screens horizontal to Main screens
object MainNavigation{
    private var lastScreenKey  =  Screens.NOTIFICATION.key
    private const val TAG = "Navigation123"

    fun goTo(screen: Screens, navigator: Navigator?, componentId: String = "1"){

        Log.i(TAG, (screen.key + "  " + lastScreenKey).toString())

        if (navigator == null || screen.key == lastScreenKey ) return

        when(screen){
            Screens.CHAT_SELECTION -> navigator.push(ChatSelectionScreen())
            Screens.SETTINGS -> navigator.push(SettingsScreen())
            Screens.NOTIFICATION -> navigator.push(NotificationScreen())

            Screens.CHAT -> navigator.push(ChatScreen(chatId = componentId ))
            Screens.CHAT_CREATION -> navigator.push((ChatCreationScreen()))
            Screens.WORK_IN_PROGRESS -> navigator.push(WorkInProgressScreen())

            Screens.ADDRESS_SELECTION -> navigator.push(AddressSelectionScreen())
            Screens.SIGN_IN -> navigator.push(SignInScreen())
            Screens.LOG_IN -> navigator.push(LogInScreen())

            Screens.DISPLAY_TENANT -> navigator.push(DisplayTenant(tenantId = componentId))
            Screens.DISPLAY_APARTMENT -> navigator.push(DisplayApartment(apartmentId = componentId))
            Screens.DISPLAY_BUILDING -> navigator.push(DisplayBuilding(buildingId = componentId))
        }
        lastScreenKey = screen.key
    }

    fun pop(navigator: Navigator?){
        if (navigator != null) {
            navigator.pop()

            lastScreenKey = navigator.lastItem.key

            Log.i(TAG, "$lastScreenKey Back")

        }
    }
}


fun showNavigationBottomBar(navigator: Navigator):Boolean =
    navigator.lastItemOrNull?.key !in listOf(Screens.CHAT.key, Screens.LOG_IN.key, Screens.SIGN_IN.key, Screens.ADDRESS_SELECTION.key, Screens.CHAT_CREATION.key)


//https://www.youtube.com/watch?v=HdXpTXHUTu0&ab_channel=AhmedGuedmioui
