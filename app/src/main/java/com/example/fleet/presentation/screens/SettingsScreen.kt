package com.example.fleet.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fleet.domain.Models.SettingsState

class SettingsScreen (
    private val settings: SettingsState,
) : BaseScreen(){
    @Composable
    override fun InnerContent() {
        Text("aaaaaaa")
    }
}
