package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.presentation.fragments.BottomBar

abstract class BaseScreen(
): Screen {

    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = { BottomBar(Modifier) }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                InnerContent()
            }
        }
    }

    @Composable
    abstract fun InnerContent()
}