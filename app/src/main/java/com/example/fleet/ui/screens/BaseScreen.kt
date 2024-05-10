package com.example.fleet.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class BaseScreen(
    //private val bottomBar: @Composable () -> Unit ={}
){

    @Composable
    fun Create() {
        Scaffold(
            // TODO Ovaj bottom bar radi probleme sa view modelom ?
            //bottomBar = bottomBar
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Content()
            }
        }
    }

    @Composable
    abstract fun Content()
}