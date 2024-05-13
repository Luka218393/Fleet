package com.example.fleet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.R
import com.example.fleet.ui.elements.BottomBar
import com.example.fleet.ui.elements.ChatBar

class ChatScreen (
    private val bottomBar: @Composable () -> Unit = {},
    private val chatBars: List<ChatBar> = listOf(
        ChatBar(
            id = 1,
            tenants = listOf(1, 2, 3),
            title = "Pokemoni",
            imageResourceId = R.drawable.lukinaikona
        ),
        ChatBar(
            id = 2,
            tenants = listOf(1, 2, 3),
            title = "Sonja Marmeladov",
            imageResourceId = R.drawable.flagicon
        ),
        ChatBar(
            id = 3,
            tenants = listOf(1, 2, 3),
            title = "Mario Marin",
            imageResourceId = R.drawable.lukinaikona
        )
    )
) : BaseScreen(){
    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(chatBars.size) { index ->
                chatBars[index].Create()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        bottomBar = { BottomBar(modifier = Modifier) }
    ).Create()
}