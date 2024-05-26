package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fleet.data.daos.chatBars
import com.example.fleet.presentation.activities.ChatActivity
import com.example.fleet.presentation.ui.theme.FleetTheme
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.activities.SettingsActivity
import com.example.fleet.presentation.ui.fragments.BottomBar

/*
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme {
                //NotificationActivity().Create(bottomBar = {BottomBar(modifier = Modifier)})
                //ChatActivity(chatBars).Create(bottomBar = {BottomBar(modifier = Modifier)})
                SettingsActivity().Create(bottomBar = {BottomBar(modifier = Modifier)})
            }
        }
    }
}