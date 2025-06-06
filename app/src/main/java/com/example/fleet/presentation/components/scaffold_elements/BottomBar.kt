package com.example.fleet.presentation.components.scaffold_elements

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.presentation.components.input_fields.InputField

@Stable
@Composable
fun NavigationBottomBar(
    visible: Boolean
) {
    val nav = LocalNavigator.current
    val modifier: Modifier = Modifier
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically (tween(300)){ it },
        exit = slideOutVertically(tween(300)){ it },

    ) {
        NavigationBar(
            modifier = modifier.height(if(FleetApplication.fleetModule.showSystemUi) 48.dp else 80.dp),
            containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
            Log.i("tag", "NavigationBar recomposed")
            NavigationBarItem(
                icon = {
                    Icon(
                        remember{Icons.Default.Star},
                        contentDescription = rememberSaveable {
                            "Notifications"
                        },
                        modifier = remember {Modifier.size(28.dp)}
                    )
                },
                onClick = {FleetApplication.fleetModule.selectedScreen = 0; MainNavigation.goTo(Screens.NOTIFICATION, nav)  },
                selected = FleetApplication.fleetModule.selectedScreen == 0,
                label = {if(!FleetApplication.fleetModule.showSystemUi) Text("Events")}

            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.square_message),
                        contentDescription = "Chat",
                        modifier = Modifier.size(28.dp)
                    )
                },
                onClick = { FleetApplication.fleetModule.selectedScreen = 1; MainNavigation.goTo(Screens.CHAT_SELECTION, nav)},
                selected = FleetApplication.fleetModule.selectedScreen == 1,
                label = {if(!FleetApplication.fleetModule.showSystemUi) Text("Chat")}

            )
            NavigationBarItem(
                icon = {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(28.dp)
                    )
                },
                onClick = {FleetApplication.fleetModule.selectedScreen = 2;  MainNavigation.goTo(Screens.SETTINGS, nav)},
                selected = FleetApplication.fleetModule.selectedScreen == 2,
                label = {if(!FleetApplication.fleetModule.showSystemUi) Text("Settings")}

            )
        }
    }
}


@Composable
fun InputBottomBar(
    modifier: Modifier = Modifier,
    send: (String) -> Unit
) {
    var text = remember{mutableStateOf("")}

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.surfaceContainer),
    ){
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                Modifier
                    .weight(4f)
                    .padding(top = 4.dp, bottom = 8.dp, start = 12.dp)){
                InputField(
                    value = text,
                    placeholder = "Message",
                    maxLines = 10,
                ) {
                    text.value = it
                }
            }
        IconButton(
            onClick = {
                send(text.value)
                text.value = ""
            },
            modifier = modifier
                .weight(1f),
            enabled = text.value.isNotEmpty()
        ){
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}
}