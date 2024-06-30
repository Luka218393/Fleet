package com.example.fleet.presentation.fragments.card_dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun PollDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var title = remember { mutableStateOf("") }
    var pollOptions by remember { mutableStateOf(listOf<PollOptionTab>()) }
    var id  by remember { mutableIntStateOf(1) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column (
            horizontalAlignment = Alignment.End

        ){
            Text(
                text = "Create Poll",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Card() {
                Column(
                ) {
                    //Todo
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {

                        Spacer(modifier = modifier.width(12.dp))

                        InputField(value = title, placeholder = "Question", maxLines = 3)
                    }

                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 0.4.dp,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    LazyColumn{
                        items(pollOptions,  key = {pollOption -> pollOption.id}) { pollOption ->
                            pollOption.Create()
                        }
                    }

                    IconButton(onClick = {
                        pollOptions +=  PollOptionTab(
                            id++,
                            removePollOption = {tabId -> pollOptions = pollOptions.minus(pollOptions.find{it.id == tabId} ?: pollOptions[0])},
                            mutableStateOf(value = "")
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Create option")
                    }

                    //Todo make polloption removeable
                }

            }
            IconButton(
                onClick = {onConfirm(title.value, pollOptions.map {it.optionValue.value})},
                modifier = modifier.padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = if(title.value.isNotEmpty() && pollOptions.isNotEmpty()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary),
                enabled = title.value.isNotEmpty() && pollOptions.isNotEmpty()
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Create Poll")
            }
        }
    }
}
class PollOptionTab (
    val id: Int,
    val removePollOption: (Int) -> Unit,
    var optionValue: MutableState<String>
){
    @Composable
    fun Create(
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { removePollOption(id) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Remove option")
            }
            InputField(value = optionValue, placeholder = "Option $id")

        }
    }
}


@Preview
@Composable
fun PollCreationPreview(){
    PollDialog(onDismiss = { }, { a, b -> })
}
@Preview
@Composable
fun PollOptionCreationTab(){
    PollOptionTab(1, {}, remember { mutableStateOf("") }).Create()
}