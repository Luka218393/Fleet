package com.example.fleet.presentation.fragments.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.FleetApplication
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import java.time.LocalDate

/**
Poll card with title, text and checkbox
TODO Display end Date

 */
class PollCard (
    private val poll: Poll,
    private val options: List<PollOption>,
    private val modifier: Modifier = Modifier,
    private val onPollOptionChange: (PollOption, PollOption?) -> Unit,
    private val showResult: Boolean
): BaseCard(poll.dateCreated, poll.creatorId,"Poll id:" + poll.id){

    @Composable
    override fun Content() {

        if (options.isEmpty()) {
            return //todo throw error("Poll has no options to display")
        }

        val selectedOption = rememberSaveable{ mutableIntStateOf(options.indexOf(options.find{it.votes.contains(FleetApplication.fleetModule.settings.value.tenantId)})) }
        val allVotes = remember { mutableIntStateOf(options.sumOf { it.votes.size }) }
        //People are able to vote
        if (poll.voteEndDate > LocalDate.now()) {
            Column {

                Title(poll.title)

                Options(
                    selectedOption,
                    allVotes,
                    options,
                    modifier,
                    onPollOptionChange,
                    showResult
                )
            }
        }
        //Only results are shown
        else{
            Column {

                Title(poll.title)

                ResultOptions(
                    allVotes,
                    options,
                    modifier
                )
            }
        }
    }
}

//Todo make this smarter
@Composable
fun Options(
    selectedOption: MutableIntState,
    allVotes: MutableIntState,
    options: List<PollOption>,
    modifier: Modifier = Modifier,
    onPollOptionChange: (PollOption, PollOption?) -> Unit,
    showResult: Boolean = false
){
    fun onClick(index: Int){
        onPollOptionChange(
            options[index],
            if (selectedOption.intValue == -1)  null else options[selectedOption.intValue],
        )
        if (selectedOption.intValue == index) selectedOption.intValue = -1
        else selectedOption.intValue = index
    }
    Column{
        for (index in options.indices) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ){
                Row(
                    modifier = modifier.clickable {onClick(index)},
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption.intValue == index,
                        onClick = {onClick(index)},
                        colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.secondary),
                        modifier = modifier.size(32.dp)
                    )

                    Text(
                        text = options[index].value,
                    )
                }
                if (showResult) {
                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        LinearProgressIndicator(
                            progress = { (options[index].votes.size / allVotes.intValue.toFloat()) },
                            modifier = Modifier
                                .weight(6f)
                                .padding(12.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = options[index].votes.count().toString(),
                            modifier = modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultOptions(
    allVotes: MutableIntState,
    options: List<PollOption>,
    modifier: Modifier = Modifier
){
    val votes: MutableList<Int> = mutableListOf<Int>().apply {for (option in options) this.add(option.votes.size) }

    Column{
        for (index in options.indices) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ){
                Text(
                    text = options[index].value,
                    color = if(votes[index] == votes.max()) MaterialTheme.colorScheme.secondary else Color.Unspecified,
                    modifier = modifier.padding(horizontal = 12.dp)
                )
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    LinearProgressIndicator(//TODO make this sometimes visible
                        progress = { (options[index].votes.size.toFloat() / allVotes.intValue.toFloat()) },//Todo make this update
                        modifier = modifier
                            .weight(6f)
                            .padding(12.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = options[index].votes.count().toString(),
                        modifier = modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PollCardPreview(){
    PollCard(polls[0], pollOptions,Modifier, { a, b  ->}, false).Content()
}