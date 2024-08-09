package com.example.fleet.domain.navigation

import cafe.adriel.voyager.core.screen.ScreenKey

enum class Screens(val key: ScreenKey){
    CHAT_SELECTION("com.example.fleet.presentation.screens.chat.ChatSelectionScreen"),
    SETTINGS("com.example.fleet.presentation.screens.settings.SettingsScreen"),
    NOTIFICATION("com.example.fleet.presentation.screens.NotificationScreen"),
    CHAT("com.example.fleet.presentation.screens.chat.ChatScreen"),
    DISPLAY_TENANT("com.example.fleet.presentation.screens.settings.display.DisplayTenant"),
    DISPLAY_APARTMENT("com.example.fleet.presentation.screens.settings.display.DisplayApartment"),
    DISPLAY_BUILDING("com.example.fleet.presentation.screens.settings.display.DisplayBuilding"),
    CHAT_CREATION("com.example.fleet.presentation.screens.chat.ChatCreationScreen"),
    WORK_IN_PROGRESS("com.example.fleet.presentation.screens.WorkInProgressScreen"),

    ADDRESS_SELECTION("com.example.fleet.presentation.screens.creation.AddressSelectionScreen"),
    SIGN_IN("com.example.fleet.presentation.screens.creation.SignInScreen"),
    LOG_IN("com.example.fleet.presentation.screens.creation.LogInScreen")
}

