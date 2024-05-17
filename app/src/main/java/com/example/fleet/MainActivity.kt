package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fleet.presentation.ui.theme.FleetTheme
import com.example.fleet.presentation.screens.NotificationScreen

/*
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleetTheme {
                NotificationScreen().Create()//pokmeoni
            }
        }
    }
}