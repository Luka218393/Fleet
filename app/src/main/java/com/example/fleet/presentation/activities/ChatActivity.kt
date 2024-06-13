package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel

class ChatActivity (
    //private val chatBars: List<ChatBar>,
    private val viewModel: ChatViewModel,

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
            items(viewModel.chatBars.size) { index ->
                viewModel.chatBars[index].Create()
            }
        }
    }

}


/*
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatActivity().Create(bottomBar = { BottomBar(modifier = Modifier) })
}

*/