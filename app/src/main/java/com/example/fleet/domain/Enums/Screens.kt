package com.example.fleet.domain.Enums

import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.data.cards
import com.example.fleet.data.chatBars
import com.example.fleet.data.settingState1
import com.example.fleet.presentation.activities.ChatActivity
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.activities.SettingsActivity

enum class Screens(val value: Screen) {
    CHAT_SCREEN(value = ChatActivity(chatBars = chatBars)),
    SETTINGS_SCREEN(value = SettingsActivity(settingState1)),
    NOTIFICATION_SCREEN(value = NotificationActivity(cards = cards))
}
