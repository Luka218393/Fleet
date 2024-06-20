package com.example.fleet.presentation.fragments

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.data.notifications
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.data.tasks
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Task
import java.util.Date

/*
Base class for event cards and poll cards
Other cards must inherit from it
*/
abstract class BaseCard(//TODo fix empty card
    val createdAt: Date,//ToDo make this time format properly
    private val createdBy: String//TODO make this reference tenant name
){

    //Content of the card
    @Composable
    abstract fun Content()

    //Creates the card composable
    @Composable
    fun Create(modifier: Modifier = Modifier){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 8.dp),
            colors =  CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            shape = RoundedCornerShape(2.dp)
            ) {

            Content()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ){
                Text(
                    text = createdBy,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = createdAt.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

/*
    Card that can display notification and all their additional content
*/
class NotificationCard (
    private val notification: Notification/*TODO make ui actually pretty*/,
    private val modifier: Modifier = Modifier
): BaseCard(notification.createdAt, notification.creatorId.toString()){
    @Composable
    override fun Content () {
        Column{
            //Additional content
            AdditionalContent(notification, modifier)
            //Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = notification.iconResId,
                    contentDescription = "${notification.iconResId} icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = modifier
                        .size(32.dp)
                )
                Spacer(modifier = modifier.width(12.dp))
                // Title text field
                Text(
                    text = notification.title,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                thickness = 0.4.dp,
                color = MaterialTheme.colorScheme.secondary
            )

            // Body text field
            Text(
                text = notification.text,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )

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

/**
Poll card with title, text and checkbox
TODO implement voting results
TODO Change opinion
TODO make poll model
 */
class PollCard (
    private val poll: Poll,
    private val options: List<PollOption>,
    private val modifier: Modifier = Modifier
): BaseCard(poll.dateCreated, poll.creatorId.toString()){
    @Composable
    override fun Content() {
        if (options.isEmpty()) {
            Log.d("PollCard", "No options")
            return/*TODO make this smarter*/
        }

        val selectedOption = remember { mutableStateOf(options[0]) }/*TODO move this to viewModel*/

        Column{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                Text(
                    text = poll.title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                thickness = 0.4.dp,
                color = MaterialTheme.colorScheme.secondary
            )

            //Poll options

            Column{
                for (index in options.indices) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ){
                        Row(
                            modifier = modifier.clickable { selectedOption.value = options[index] },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedOption.value == options[index],
                                onClick = { selectedOption.value = options[index] },
                                colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.secondary),
                                modifier = modifier.size(32.dp)
                            )

                            Text(
                                text = options[index].value,
                            )
                        }

                        LinearProgressIndicator(//TODO make this sometimes visible
                            progress = { 0.3f },//Todo make this update
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

//TODO add task result card

//Todo create TaskSetCard
//Todo make clickable and make finishable
class TaskCard (
    private val task: Task,
    private val onCheckboxChange: (Boolean) -> Unit,
    private val modifier: Modifier = Modifier
): BaseCard(task.createdAt, task.creatorId.toString()){
    @Composable
    override fun Content() {
        Column{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = task.title,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                thickness = 0.4.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Row (
                modifier = modifier.height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = task.description,
                    modifier = modifier
                        .padding(12.dp)
                        .weight(8f),
                    style = MaterialTheme.typography.bodyMedium
                )
                VerticalDivider(
                    modifier = modifier.fillMaxHeight(),
                    thickness = 0.4.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Box(
                    modifier = modifier.weight(2f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(//TODO Make this invisible
                        imageVector = Icons.Default.Check,
                        contentDescription = "icon",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = modifier
                            .size(32.dp)
                    )
                }
            }
            HorizontalDivider(
                modifier = modifier.fillMaxWidth()
                    .padding(bottom = 4.dp),
                thickness = 0.4.dp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
@Preview
fun NotificationCardPreview(){
    NotificationCard(notifications[2]).Content()
}

@Composable
@Preview
fun PollCardPreview(){
    PollCard(polls[0], pollOptions).Content()
}
@Composable
@Preview
fun TaskCardPreview(){
    TaskCard(tasks[0], {}).Content()
}

