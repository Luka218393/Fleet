package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.fleet.presentation.ui.theme.FleetTheme
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.ui.elements.BottomBar

/*
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleetTheme {
                NotificationScreen(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)).Create(bottomBar = {BottomBar(modifier = Modifier)})
            }
        }
    }
}