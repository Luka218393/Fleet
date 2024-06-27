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
import com.example.fleet.presentation.HelperFunctions


//Todo make message boxes if
// 1) you sent message
// 2) second tenant sent it
// 3) You are in a group
//Todo Send images

@Composable
fun CreateMessageBox(
    modifier: Modifier = Modifier,
    message: Message,
    tenantId: Int,

){
    Box(
        modifier = modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp)),
        contentAlignment = if(tenantId == message.senderId) Alignment.TopEnd else Alignment.TopStart
    ){
        Card(
            modifier = modifier.padding(4.dp)
                .widthIn(max = 300.dp, min = 100.dp),
        ){
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.padding(4.dp)
            ){
                if (message.senderId != tenantId) {
                    Text(
                        text = /*FleetApplication.fleetModule.getTenantName(message.senderId) ?:*/ "No name",//Todo This greatly slows down the app
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Box{
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.bodyMedium

                    )
                }

                Text(
                    text = HelperFunctions.getMessageDate(message.sendingTime),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
@Preview
@Composable
fun MessageBoxPreview(){
    CreateMessageBox(message = messages[0], tenantId = 2)
}