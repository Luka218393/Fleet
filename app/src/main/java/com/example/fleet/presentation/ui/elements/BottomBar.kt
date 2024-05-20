package com.example.fleet.presentation.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier) {
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .height(35.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /* Handle Favorite button click */ }) {
                Icon(
                    Icons.Default.Star, contentDescription = "Notifications",
                    modifier = modifier.fillMaxSize()
                )
            }
            IconButton(onClick = { /* Handle Shopping button click */ }) {
                Icon(Icons.Default.Home, contentDescription = "Chat",
                modifier = modifier.fillMaxSize())
            }
            IconButton(onClick = { /* Handle Settings button click */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings",
                modifier = modifier.fillMaxSize())
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(Modifier)
}