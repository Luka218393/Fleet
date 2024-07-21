package com.example.fleet.presentation.fragments.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.data.notifications
import com.example.fleet.domain.Models.Notification

//
class NotificationCard (
    private val notification: Notification,
    private val modifier: Modifier = Modifier
): BaseCard(notification.createdAt, notification.creatorId, "Notification id:" + notification.id){

    //
    @Composable
    override fun Content () {
        Column{

            AdditionalContent(notification, modifier)

            Title(title = notification.title)

            Text(
                text = notification.text,
                modifier = modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }


    //
    //Additional content of the card
    //Currently can only display image
    @Composable
    fun AdditionalContent(
        notification: Notification,
        modifier: Modifier = Modifier
    ){
        Column (
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if (notification.imageResId != null) {
                Image(
                    painter = painterResource(notification.imageResId!!),
                    contentDescription = "Notifications picture",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(256.dp)
                        .padding(vertical = 12.dp)
                )
                return
            }
        }
    }
}

@Composable
@Preview
fun NotificationCardPreview(){
    NotificationCard(notifications[2]).Content()
}