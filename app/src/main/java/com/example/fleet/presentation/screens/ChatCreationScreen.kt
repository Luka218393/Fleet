package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.TopBar

class ChatCreationScreen: Screen{
    @Composable
    override fun Content() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(modifier = Modifier, title = "ChatCreationScreen")},
            bottomBar = { BottomBar() },
            ) { padding ->
            Box(modifier = Modifier.padding(padding)) {

            }
        }
    }
}