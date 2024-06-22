package com.example.fleet.presentation.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.data.chats
import com.example.fleet.domain.Models.Chat

class ChatBar (
    private val chat: Chat,
    private val getLastMessageText: () -> String,
    private val modifier: Modifier = Modifier,
){
    /*TODO add color change based on discussion type -> ViewModel*/
    @Composable
    fun Create(
        navigateToDialogueScreen: () -> Unit
    ){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(1.dp)
                .clickable {navigateToDialogueScreen()},
            shape = RoundedCornerShape(2.dp)


        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxSize()
            ){
                Image(painter = painterResource(
                    chat.profileImageResId ?: R.drawable.lukinaikona),
                    contentDescription = null,
                    modifier = modifier
                        .size(60.dp)
                        .weight(2f)
                )
                Spacer(modifier = modifier.weight(0.2f))
                Column(
                    modifier = modifier
                        .weight(8f),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = chat.title ?: "No title", style = MaterialTheme.typography.titleLarge, modifier = modifier
                        .align(Alignment.Start))
                    Text(text = getLastMessageText(), style = MaterialTheme.typography.bodyMedium,modifier = modifier
                        .align(Alignment.Start))
                }

                //TODO make this visible when there are new messages in ViewModel
                Icon(imageVector = Icons.Default.Star, contentDescription = "New message", tint = MaterialTheme.colorScheme.secondary, modifier = modifier.weight(1f))
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatBar(chats[0],{"yeaaa"}, Modifier).Create {}
}