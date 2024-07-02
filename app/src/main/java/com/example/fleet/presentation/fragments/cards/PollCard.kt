package com.example.fleet.presentation.fragments.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption

/**
Poll card with title, text and checkbox
TODO implement voting results
TODO Change opinion
TODO make poll model
 */
class PollCard (
    private val poll: Poll,
    private val options: List<PollOption>,
    private val modifier: Modifier = Modifier,
    private val onPollOptionChange: (PollOption, PollOption?) -> Unit = { a, b ->}
): BaseCard(poll.dateCreated, poll.creatorId,"Poll id:" + poll.id){

    @Composable
    override fun Content() {

        if (options.isEmpty()) {
            return //todo throw error("Poll has no options to display")
        }

        val selectedOption = rememberSaveable{ mutableIntStateOf(-1) } //Todo make poll remember tenants vote
        val allVotes = remember { mutableIntStateOf(options.sumOf { it.votes }) }
        Column{

            Title()

            //Todo when you restart app you can vote many times
            Options(selectedOption, allVotes)
        }
    }
    @Composable
    fun Title(){
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

    }

    @Composable
    fun Options(
        selectedOption: MutableIntState,
        allVotes: MutableIntState
    ){
        Column{
            for (index in options.indices) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ){
                    Row(
                        modifier = modifier.clickable {
                            onPollOptionChange(
                                options[index],
                                if (selectedOption.intValue == -1)  null else options[selectedOption.intValue],
                            )
                            if (selectedOption.intValue == index) selectedOption.intValue = -1
                            else selectedOption.intValue = index
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption.intValue == index,
                            onClick = {
                                onPollOptionChange(
                                    options[index],
                                    if (selectedOption.intValue == -1) null else options[selectedOption.intValue],
                                )
                                if (selectedOption.intValue == index) selectedOption.intValue = -1
                                else selectedOption.intValue = index
                            },
                            colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.secondary),
                            modifier = modifier.size(32.dp)
                        )

                        Text(
                            text = options[index].value,
                        )
                    }
                    Row (
                        modifier = modifier.fillMaxWidth()
                    ){
                        LinearProgressIndicator(//TODO make this sometimes visible
                            progress = { (  options[index].votes.toFloat() / allVotes.intValue.toFloat()) },//Todo make this update
                            modifier = Modifier
                                .weight(6f)
                                .padding(12.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = options[index].votes.toString(),
                            modifier = modifier.weight(1f)
                        )
                    }

                }
            }
        }
    }

}


@Composable
@Preview
fun PollCardPreview(){
    PollCard(polls[0], pollOptions,Modifier, { a, b  ->}).Content()
}