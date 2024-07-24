package com.example.fleet.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.domain.Navigation
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar

class WorkInProgressScreen: Screen {

    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        Scaffold(
            bottomBar = { BottomBar()}
        ){ padding ->

            Box(
                modifier = Modifier.padding(padding)
                    .fillMaxSize()
                    .clickable { Navigation.pop(nav) },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "This part of the app still isn't finished",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}