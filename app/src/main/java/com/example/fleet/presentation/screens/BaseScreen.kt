package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.FloatingButton
import com.example.fleet.presentation.fragments.scaffold_elements.InputBottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.TopBar

abstract class BaseScreen(
    @Transient
    private val floatingButton: List<() -> Unit> = emptyList(),
    @Transient
    private val bottomBar: Boolean = false,
    @Transient
    private val inputBottomBar: ((String) -> Unit)? = null,
    @Transient
private val topBar: String? = null,
): Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { if ( topBar != null ) { TopBar(Modifier, topBar) } },
            bottomBar = {
                if (bottomBar)BottomBar()
                else if (inputBottomBar != null){
                    InputBottomBar(
                    modifier = Modifier,
                    send = inputBottomBar
            ) } },
            floatingActionButton = { if(floatingButton.isNotEmpty()) FloatingButton(
                toggleNotificationDialog = floatingButton[0],
                toggleTaskDialog =  floatingButton[1] ,
                togglePollDialog =  floatingButton[2]
            )}
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                InnerContent()
            }
        }
    }

    @Composable
    abstract fun InnerContent()
}