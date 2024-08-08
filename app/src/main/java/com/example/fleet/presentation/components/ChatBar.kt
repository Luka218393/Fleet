package com.example.fleet.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.HelperFunctions


@Composable
fun ChatBar(
    navigateToChatScreen:(String)->Unit,
    chat: Chat,
    lastMessageText: String
) {
    val modifier = Modifier

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(vertical = 1.dp)
                .clickable { navigateToChatScreen(chat.id) }
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            Image(
                painter = painterResource(
                    chat.profileImageResId ?: R.drawable.account_icon
                ),
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
            ) {
                Text(
                    text = chat.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.align(Alignment.Start),
                    maxLines = 1,
                    color = if (chat.isPrivate) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = HelperFunctions.cutString(text = lastMessageText),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.align(Alignment.Start),
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.outline //TOdo make message turn color to primary if it is new message
                )
                //TODO make this visible when there are new messages in ViewModel
            }
        }

}

//
@Composable
fun SimplifiedChatBar(
    tenant: Tenant,
    isBarSelected: (String) -> Boolean,
    onClick:(String) -> Unit,
    onDismiss: (String) -> Unit
) {
    val modifier = Modifier
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(1.dp)
            .clickable { if (isBarSelected(tenant.id)) onDismiss(tenant.id) else onClick(tenant.id) },
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(containerColor = if(isBarSelected(tenant.id)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    tenant.profileImageRes ?: R.drawable.lukinaikona
                ),
                contentDescription = null,
                modifier = modifier
                    .size(60.dp)
                    .weight(2f)
            )
            Spacer(modifier = modifier.weight(0.2f))
            Text(
                text = tenant.name + " " + tenant.surname,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .padding()
                    .weight(8f),
                maxLines = 1,
            )
        }
    }
}
