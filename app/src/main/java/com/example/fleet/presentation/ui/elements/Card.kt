package com.example.fleet.presentation.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption

/*
Base class for event cards and poll cards
Other cards must inherit from it
*/
abstract class BaseCard{

    //Content of the card
    @Composable
    abstract fun Content()

    //Creates the card composable
    @Composable
    fun Create(modifier: Modifier = Modifier){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            colors =  CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {

            Content()

        }
    }
}

/*
    Card that can display notification and all their additional content
*/
class NotificationCard (
    private val notification: Notification/*TODO make ui actually pretty*/,
    private val modifier: Modifier = Modifier
): BaseCard(){
    @Composable
    override fun Content () {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            //Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = notification.iconResId,
                    contentDescription = "${notification.iconResId} icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )
                // Title text field
                Text(
                    text = notification.title,
                    modifier = Modifier
                        .weight(4f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            // Body text field
            Text(text = notification.text,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            //Additional content
            AdditionalContent(notification, modifier)

        }
    }

    //Additional content of the card
    //Currently can only display image
    @Composable
    fun AdditionalContent(
        notification: Notification,
        modifier: Modifier = Modifier
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if (notification.imageResId != null) {
                Image(painter = painterResource(notification.imageResId ?: R.drawable.lukinaikona),
                    contentDescription = "Picture of the event",
                    modifier = modifier.size(200.dp)
                )
                return
            }
        }
    }
}

/*
Poll card with title, text and checkbox
TODO implement voting results
TODO Change opinion
TODO make poll model
 */
class PollCard (
    private val poll: Poll,
    private val options: List<PollOption>,
): BaseCard(){
    @Composable
    override fun Content() {
        if (options.isEmpty()) {
            return/*TODO make this smarter*/
        }
        val selectedOption = remember { mutableStateOf(options[0]) }/*TODO move this to viewModel*/

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "Poll icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )
                // Question text field
                Text(
                    text = poll.question,
                    modifier = Modifier
                        .weight(4f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            //Poll options

            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                for (index in options.indices) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(30.dp)
                            .clickable { selectedOption.value = options[index] },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption.value == options[index],
                            onClick = { selectedOption.value = options[index] },
                            colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.secondary)
                        )

                        Text(
                            text = options[index].value,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

class TaskCard (
    private val question: String,
    private val options: List<PollOption>,
): BaseCard(){
    @Composable
    override fun Content() {
        //TODO make task card
    }

}



@Preview(showBackground = true)
@Composable
fun PreviewSimpleEventCard() {
}
@Preview(showBackground = true)
@Composable
fun PreviewImageEventCard() {
}
@Preview(showBackground = true)
@Composable
fun PreviewPollCard() {
}