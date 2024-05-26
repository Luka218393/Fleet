package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.presentation.ui.fragments.BottomBar
import com.example.fleet.presentation.ui.fragments.ChatBar

class ChatActivity (
    private val chatBars: List<ChatBar>
) : BaseActivity(){
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

/*
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatActivity().Create(bottomBar = { BottomBar(modifier = Modifier) })
}*/