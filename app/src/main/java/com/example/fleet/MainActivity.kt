package com.example.fleet

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.presentation.animations.CustomScreenTransition
import com.example.fleet.presentation.components.scaffold_elements.NavigationBottomBar
import com.example.fleet.presentation.screens.NotificationScreen
import com.example.fleet.presentation.ui.theme.FleetTheme

/**
This is the starting point of the app.
*/
//Todo what is a service???
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme (
                content = {


                    Navigator(
                        NotificationScreen(),
                        onBackPressed = { true }
                    ){ navigator ->

                        Scaffold(
                            bottomBar = { NavigationBottomBar()},
                        ) { padding ->

                            Box(modifier = Modifier.padding(padding)) {
                                CustomScreenTransition(navigator) { screen ->
                                    screen.Content()
                                }
                            }
                        }
                    }




                    val insetsController = WindowCompat.getInsetsController(
                        (LocalView.current.context as Activity).window,
                        LocalView.current
                    )
                    if (!LocalView.current.isInEditMode) {
                        if (!FleetApplication.fleetModule.showSystemUi) {
                            insetsController.apply {
                                hide(WindowInsetsCompat.Type.navigationBars())
                                systemBarsBehavior =
                                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                            }
                        } else {
                            insetsController.apply { show(WindowInsetsCompat.Type.navigationBars()) }
                        }
                    }
                }
            )
        } //Todo rename notifications to events
    }
}
//https://stackoverflow.com/questions/69734451/is-there-a-way-to-create-scroll-wheel-in-jetpack-compose

//https://developer.android.com/develop/ui/compose/touch-input/pointer-input/drag-swipe-fling

//https://yqnn.github.io/svg-path-editor/

