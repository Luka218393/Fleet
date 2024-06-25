package com.example.fleet.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

//Todo make all of theese functions objects
@Composable
fun CreateNotificationDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }


    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card() {
                Column(
                ) {
                    //Todo add marks
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,//Todo
                            contentDescription = "Add icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = modifier
                                .size(32.dp)
                        )
                        Spacer(modifier = modifier.width(12.dp))
                        // Title text field
                        BasicTextField(value = title, onValueChange = { title = it })
                        //https://developer.android.com/develop/ui/compose/text/user-input
                    }

                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 0.4.dp,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    // Body text field
                    BasicTextField(
                        value = text,
                        modifier = Modifier.padding(12.dp),
                        onValueChange = { text = it }
                    )
                    IconButton(onClick = {onConfirm(title, text)}) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Create Notification")
                    }
                }
            }
        }
    )
}

@Composable
fun CreateTaskDialog(
    onDismiss: () -> Unit,
    //createNotification: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }


    Dialog(
        onDismissRequest = onDismiss,
        content = {
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
                        Icon(
                            imageVector = Icons.Default.Add,//Todo
                            contentDescription = "Add icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = modifier
                                .size(32.dp)
                        )
                        Spacer(modifier = modifier.width(12.dp))
                        // Title text field
                        BasicTextField(value = title, onValueChange = { title = it })
                        //https://developer.android.com/develop/ui/compose/text/user-input
                    }

                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 0.4.dp,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    // Body text field
                    BasicTextField(
                        value = text,
                        modifier = Modifier.padding(12.dp),
                        onValueChange = { text = it }
                    )
                }
            }
        }
    )
}


object PollDialog{
    @Composable
    fun Create(
        onDismiss: () -> Unit,
        onConfirm: (String, List<String>) -> Unit,
        modifier: Modifier = Modifier
    ) {
        var title by remember { mutableStateOf("") }
        var pollOptions by remember { mutableStateOf(listOf<PollOptionTab>()) }
        var id  by remember { mutableIntStateOf(1) }
        
        Dialog(
            onDismissRequest = onDismiss
        ) {
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
                        Icon(
                            imageVector = Icons.Default.Add,//Todo
                            contentDescription = "Add icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = modifier
                                .size(32.dp)
                        )
                        Spacer(modifier = modifier.width(12.dp))
                        // Title text field
                        BasicTextField(value = title, onValueChange = { title = it })
                        //https://developer.android.com/develop/ui/compose/text/user-input
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
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Create Poll")
                    }
                    IconButton(onClick = {onConfirm(title, pollOptions.map {it.optionValue.value})}) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Create Notification")
                    }

            //Todo make polloption removeable
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
                BasicTextField(
                    value = optionValue.value,
                    onValueChange = { optionValue.value = it },
                    Modifier
                        .fillMaxWidth()
                        .background(color = Blue)
                        .height(32.dp)
                )
            }
        }
    }
}





@Preview
@Composable
fun NotificationCreationPreview(){
    CreateNotificationDialog({},{a,b ->})
}

@Preview
@Composable
fun TaskCreationPreview(){
    CreateTaskDialog(onDismiss = { })
}

@Preview
@Composable
fun PollCreationPreview(){
    PollDialog.Create(onDismiss = { },{a,b->})
}
@Preview
@Composable
fun PollOptionCreationTab(){
    PollDialog.PollOptionTab (1,{}, remember{mutableStateOf("")}).Create()
}