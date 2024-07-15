package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.ui.theme.FleetTheme

/**
This is the starting point of the app.
*/
//Todo what is a service???
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme (
                settings = FleetApplication.fleetModule.settings.value,
                content = {
                    Navigator(NotificationScreen())

                }
            )
        }
    }
}
//https://stackoverflow.com/questions/69734451/is-there-a-way-to-create-scroll-wheel-in-jetpack-compose



