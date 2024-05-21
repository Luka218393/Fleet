package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

                //Todo Make this smarter
                //Hides home and back buttons
                WindowCompat.setDecorFitsSystemWindows(window, false)
                val controller = WindowCompat.getInsetsController(window, window.decorView)
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.addOnControllableInsetsChangedListener { _, typeMask ->
                    var systemBarsVisible = typeMask and WindowInsetsCompat.Type.systemBars() != 0
                }


                NotificationScreen().Create(bottomBar = {BottomBar(modifier = Modifier)})//NotificationScreen(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)).Create(bottomBar = {BottomBar(modifier = Modifier)})
            }
        }
    }
}