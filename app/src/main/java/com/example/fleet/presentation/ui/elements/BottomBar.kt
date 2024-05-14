package com.example.fleet.presentation.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier) {
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { /* Handle Home button click */ }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = { /* Handle Favorite button click */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /* Handle Shopping button click */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
            }
            IconButton(onClick = { /* Handle Settings button click */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    }
}