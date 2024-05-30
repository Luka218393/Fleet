package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.pollOptions
import com.example.fleet.data.settingState1
import com.example.fleet.domain.Navigation
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.ui.theme.FleetTheme
import com.example.fleet.presentation.activities.SettingsActivity
import com.example.fleet.presentation.ui.fragments.BottomBar
import kotlinx.coroutines.runBlocking

/*
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme {
                val database = FleetDatabase.getDatabase(context = this)
                runBlocking { database.pollOptionDao().delete(pollOptions[2]) }

                //NotificationActivity().Create(bottomBar = {BottomBar(modifier = Modifier)})
                //ChatActivity(chatBars).Create(bottomBar = {BottomBar(modifier = Modifier)})
                //SettingsActivity(settingState1).Create(bottomBar = {BottomBar(modifier = Modifier)})
                Navigator(NotificationActivity())
            }
        }
    }
}