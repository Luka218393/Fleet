package com.example.fleet.presentation.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton() {
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = !expanded },
            ) {
                if (expanded) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        //isFloatingActionButtonDocked = true,
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (expanded) {
                    // Display expanded menu with four buttons
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Replace these buttons with your actual actions
                        FloatingMenuButton(icon = Icons.Default.Favorite, onClick = { /* Handle Favorite */ })
                        FloatingMenuButton(icon = Icons.Default.Add, onClick = { /* Handle Bookmark */ })
                        FloatingMenuButton(icon = Icons.Default.Share, onClick = { /* Handle Share */ })
                        FloatingMenuButton(icon = Icons.Default.Delete, onClick = { /* Handle Delete */ })
                    }
                }
            }
        }
    )
}

@Composable
fun FloatingMenuButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
            .padding(8.dp),
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFloatingMenuScreen() {
    FloatingButton()
}
