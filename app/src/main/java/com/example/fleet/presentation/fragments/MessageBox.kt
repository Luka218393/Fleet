package com.example.fleet.presentation.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.data.messages
import com.example.fleet.domain.Models.Message


//Todo make message boxes if
// 1) you sent message
// 2) second tenant sent it
// 3) You are in a group
class MessageBox(
    private val message: Message
) {
    @Composable
    fun Create(
        modifier: Modifier = Modifier,
        tenantId: Int,


    ){
        Box(
            modifier = Modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            contentAlignment = if(tenantId == message.senderId) Alignment.TopEnd else Alignment.TopStart
        ){
            Card(
                modifier = Modifier.padding(4.dp)
                    .widthIn(max = 300.dp),
            ){
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(4.dp)
                ){
                    Text(
                        text = "Pokemon",//Todo make this display persons name
                        style = MaterialTheme.typography.bodySmall
                        )
                    Box(

                    ){
                        Text(
                            text = message.text,
                            style = MaterialTheme.typography.bodyMedium

                        )
                    }

                    Text(
                        text = message.sendingTime.toString(),//Todo MAke this display time nicely
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MessageBoxPreview(){
    MessageBox(messages[0]).Create(tenantId = 1)
}