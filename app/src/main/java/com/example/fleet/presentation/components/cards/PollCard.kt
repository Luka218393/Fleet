package com.example.fleet.presentation.components.cards

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import java.time.LocalDate


//TODO Display end Date
//
class PollCard (
    private val poll: Poll,
    private val options: List<PollOption>,
    private val onPollOptionChange: (PollOption, PollOption?) -> Unit,
    private val modifier: Modifier = Modifier,

): BaseCard(poll.dateCreated, poll.creatorId,"Poll id:" + poll.id){

    //
    @Composable
    override fun Content() {

        if (options.isEmpty()) {
            //throw error("Poll has no options to display")
        }


        //People are able to vote
        if (poll.endDate > LocalDate.now()) {
            Column {

                Title(poll.title)

                Options(
                    options,
                    modifier,
                    onPollOptionChange,
                    poll.resultsVisible
                )
            }
        }

        //Only results are shown
        else{
            Column {

                Title(poll.title)

                ResultOptions(
                    options,
                    modifier
                )
            }
        }
    }
}

//
@Composable
fun Options(
    options: List<PollOption>,
    modifier: Modifier = Modifier,
    onPollOptionChange: (PollOption, PollOption?) -> Unit,
    resultVisible: Boolean
){
    var selectedOptionIndex by rememberSaveable{ mutableIntStateOf( options.indexOf( options.find{ it.votes.contains( FleetApplication.fleetModule.tenantId ) } ) ) }

    //
    fun onClick(index: Int){
        onPollOptionChange(
            options[index],
            if (selectedOptionIndex == -1)  null
            else options[selectedOptionIndex],
        )

        if (selectedOptionIndex == index) selectedOptionIndex = -1
        else selectedOptionIndex = index
    }
    //
    Column{

        for (index in options.indices) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ){

                //
                Row(
                    modifier = modifier.clickable {onClick(index)},
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOptionIndex == index,
                        onClick = {onClick(index)},
                        colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.primary),
                        modifier = modifier.size(32.dp)
                    )

                    Text(
                        text = options[index].value,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                //
                if (resultVisible) {
                    VotesCounter(options = options, index = index)
                }
            }
        }
    }
}

//
@Composable
fun ResultOptions(
    options: List<PollOption>,
    modifier: Modifier = Modifier
){
    val votes: MutableList<Int> = mutableListOf<Int>().apply { for (option in options) this.add(option.votes.size) }

    Column{
        for (index in options.indices) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ){

                Text(
                    text = options[index].value,
                    color = if(votes[index] == votes.max()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = modifier.padding(horizontal = 12.dp),
                    style = if(votes[index] == votes.max()) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                )

                VotesCounter(options = options, index = index)

            }
        }
    }
}

//
@Composable
fun VotesCounter(
    options: List<PollOption>,
    index: Int,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        LinearProgressIndicator(
            progress = { (options[index].votes.size.toFloat() / options.maxBy { it.votes.size }.votes.size.toFloat()) },
            modifier = modifier
                .weight(6f)
                .padding(12.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.inverseOnSurface
        )
        Text(
            text = options[index].votes.size.toString(),
            modifier = modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}