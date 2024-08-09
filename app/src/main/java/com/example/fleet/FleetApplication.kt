package com.example.fleet

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.fleet.domain.navigation.ChatScreens
import com.example.fleet.presentation.screens.chat.ChatCreationScreen
import com.example.fleet.presentation.screens.chat.ChatScreen

class FleetApplication : Application(){

    companion object{
        lateinit var fleetModule: FleetModule
        val viewModelStore: ViewModelStore = ViewModelStore()//Todo maybe make this bind to MainActivity in future (this is used in viewModels)

    }

    override fun onCreate() {
        super.onCreate()
        fleetModule = ViewModelProvider(viewModelStore, FleetModuleFactory(this))[FleetModule::class.java]

        ScreenRegistry {
            register<ChatScreens.ChatCreationScreen> {
                ChatCreationScreen()
            }
            register<ChatScreens.ChatScreen> { provider ->
                ChatScreen(chatId = provider.chatId)
            }
        }
    }
}