package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class BaseActivity(
    //private val bottomBar: @Composable () -> Unit ={}
){

    @Composable
    fun Create(bottomBar: @Composable () -> Unit) {
        Scaffold(
            bottomBar = bottomBar
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Content()
            }
        }
    }

    @Composable
    abstract fun Content()
}