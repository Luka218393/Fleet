package com.example.fleet.presentation.components.scaffold_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
) {
    val nav = LocalNavigator.current

    NavigationBar(
        modifier = modifier.height(if(FleetApplication.fleetModule.showSystemUi) 48.dp else 60.dp )
        ) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(28.dp)
                )
            },
            onClick = {FleetApplication.fleetModule.selectedScreen = 0; Navigation.goTo(Screens.NOTIFICATION, nav)  },
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
            onClick = { FleetApplication.fleetModule.selectedScreen = 1; Navigation.goTo(Screens.CHAT_SELECTION, nav)},
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
            onClick = {FleetApplication.fleetModule.selectedScreen = 2;  Navigation.goTo(Screens.SETTINGS, nav)},
            selected = FleetApplication.fleetModule.selectedScreen == 2,
            label = {if(!FleetApplication.fleetModule.showSystemUi) Text("Settings")}

        )
    }
}

//Todo change text style
@Composable
fun InputBottomBar(
    modifier: Modifier = Modifier,
    send: (String) -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
    ){
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){


        var text by remember{mutableStateOf("")}
        BasicTextField(
            value = text,
            onValueChange = {text = it},
            modifier = modifier
                //.width(250.dp)
                .weight(3f)
                ,
            decorationBox = {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                        .width(300.dp)
                        .heightIn(min = 30.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        it()
                    }
                }
            },
        )

        IconButton(
            onClick = {
                send(text)
                text = ""
            },
            modifier = modifier
                .weight(1f)
                .fillMaxHeight(),
            enabled = text.isNotEmpty()
        ){
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}
}