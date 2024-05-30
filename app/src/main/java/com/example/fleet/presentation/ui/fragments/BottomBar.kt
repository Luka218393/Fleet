package com.example.fleet.presentation.ui.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation

@Composable
fun BottomBar(modifier: Modifier) {
    val navigator = Navigation()
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
                IconButton(onClick = { navigator.goTo(Screens.NOTIFICATION_SCREEN, nav )}) {
                    Icon(
                        Icons.Default.Star, contentDescription = "Notifications",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { navigator.goTo(screen = Screens.CHAT_SCREEN, nav) }) {
                    Icon(
                        Icons.Default.Home, contentDescription = "Chat",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { navigator.goTo(Screens.SETTINGS_SCREEN, nav) }) {
                    Icon(
                        Icons.Default.Settings, contentDescription = "Settings",
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    )     //Spacer(modifier = Modifier.height(50.dp))
}
//https://www.youtube.com/watch?v=c8XP_Ee7iqY&ab_channel=PhilippLackner TODO
@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(Modifier)
}