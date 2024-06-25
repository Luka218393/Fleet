package com.example.fleet.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation

//Todo move this to object
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
) {
    val nav = LocalNavigator.current
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        actions = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(36.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { Navigation.goTo(Screens.NOTIFICATION, nav )}) {
                    Icon(
                        Icons.Default.Star, contentDescription = "Notifications",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { Navigation.goTo(screen = Screens.CHAT_SELECTION, nav) }) {
                    Icon(
                        Icons.Default.Home, contentDescription = "Chat",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { Navigation.goTo(Screens.DISPLAY, nav) }) {
                    Icon(
                        Icons.Default.Settings, contentDescription = "Settings",
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}

//Todo Make this functional and preatty
@Composable
fun InputBottomBar(
    modifier: Modifier,
    send: (String) -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ){
        var text by remember{mutableStateOf("")}
        BasicTextField(
            value = text,
            onValueChange = {text = it},
            modifier = Modifier
                .padding(4.dp)
                .weight(4f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))//Todo make so that text doenst clip
                .background(MaterialTheme.colorScheme.tertiary)
        )

        IconButton(
            onClick = {send(text)},
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
        ){
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}

