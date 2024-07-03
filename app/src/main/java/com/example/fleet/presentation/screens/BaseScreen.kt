package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.FloatingButton

abstract class BaseScreen(
    @Transient
    private val floatingButton: List<()->Unit> = emptyList()
): Screen {

    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = { BottomBar(Modifier) },
            floatingActionButton = { if(floatingButton.isNotEmpty()) FloatingButton(floatingButton[0],floatingButton[1],floatingButton[2]) }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                InnerContent()
            }
        }
    }

    @Composable
    abstract fun InnerContent()
}