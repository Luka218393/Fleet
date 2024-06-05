package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Navigation
import com.example.fleet.presentation.fragments.BottomBar
import com.example.fleet.presentation.fragments.ChatBar

class ChatActivity (
    private val chatBars: List<ChatBar>,
    navigation: Navigation
) : BaseActivity(navigation){
    @Composable
    override fun InnerContent() {
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