package com.example.fleet.presentation.ui.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.domain.Enums.ChatType
import com.example.fleet.domain.Models.Chat

class ChatBar (
    private val chat: Chat,
    private val modifier: Modifier = Modifier,
){
    /*TODO add color change based on discussion type -> ViewModel*/
    @Composable
    fun Create(){
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp, 4.dp)
                .clickable {/*TODO add navigation to chat*/ },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(painter = painterResource(
                    chat.profileImageResId ?: R.drawable.lukinaikona),
                    contentDescription = null,
                    modifier = modifier
                        .size(64.dp)
                        .weight(1f)
                        .padding(8.dp)
                )
                Column(
                    modifier = modifier
                        .weight(4f)
                        .padding(8.dp)
                ){
                    Text(text = chat.title ?: "No title", style = MaterialTheme.typography.titleLarge, modifier = modifier
                        .padding(0.dp)
                        .align(Alignment.Start))
                    Text(text = getLastMessage(), style = MaterialTheme.typography.bodyMedium,modifier = modifier
                        .padding(0.dp)
                        .align(Alignment.Start))
                }

                //TODO make this visible when there are new messages in ViewModel
                Icon(imageVector = Icons.Default.Star, contentDescription = "New message", tint = MaterialTheme.colorScheme.secondary, modifier = modifier.weight(1f))
            }
        }
    }

    fun getLastMessage(): String{
        return "This should be last message"
    }
}


/*
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatBar(

    ).Create()
}*/