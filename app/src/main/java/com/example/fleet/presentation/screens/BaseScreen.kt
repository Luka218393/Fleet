package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

abstract class BaseScreen(
    @Transient
    private val floatingButton: @Composable () -> Unit,
    @Transient
    private val topBar: @Composable () -> Unit,
    @Transient
    private val bottomBar: @Composable () -> Unit
): Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = topBar,
            bottomBar =  bottomBar ,
            floatingActionButton = floatingButton
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                InnerContent()
            }
        }
    }

    @Composable
    abstract fun InnerContent()
}