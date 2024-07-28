package com.example.fleet.presentation.components.scaffold_elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatingButton(
    toggleNotificationDialog: () -> Unit,
    toggleTaskDialog: () -> Unit,
    togglePollDialog:() -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        if (expanded){
            IconButton(onClick = { toggleNotificationDialog() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create notification dialog")
            }
            IconButton(onClick = { toggleTaskDialog()}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create task dialog")
            }
            IconButton(onClick = { togglePollDialog() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create poll dialog")
            }
        }
        FloatingActionButton(
            onClick = { expanded = !expanded },
            shape = CircleShape,
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            if (expanded) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun SimpleFloatingButton(
    onclick: () -> Unit,
    icon: ImageVector
) {
    FloatingActionButton(
        onClick = onclick,
        shape = CircleShape,
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "New Chat",
            tint = Color.White
        )
    }
}