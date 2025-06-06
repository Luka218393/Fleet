package com.example.fleet.presentation.components.cards.card_dialogs

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fleet.presentation.components.input_fields.InputField


@Composable
fun TaskDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val title = remember { mutableStateOf("") }
    var subTasks by remember { mutableStateOf(listOf<SubTaskTab>()) }
    var id  by remember { mutableIntStateOf(1) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column (
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = "Create Task",
                modifier = modifier.fillMaxWidth().padding(12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            Card{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {

                        Spacer(modifier = modifier.width(12.dp))

                        InputField(value = title, placeholder = "Title", maxLines = 1){title.value = it}
                    }

                    HorizontalDivider(
                        modifier = modifier.fillMaxWidth(),
                        thickness = 0.4.dp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    LazyColumn{
                        items(subTasks,  key = {subTasks -> subTasks.id}) { subTask ->
                            subTask.Create()
                        }
                    }

                    IconButton(onClick = {
                        subTasks +=  SubTaskTab(
                            id++,
                            removePollOption = {tabId -> subTasks = subTasks.minus(subTasks.find{it.id == tabId} ?: subTasks[0])},
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Create subtask",tint = MaterialTheme.colorScheme.primary)
                    }
                }

            }
            IconButton(
                onClick = {onConfirm(title.value, subTasks.map {it.value.value})},
                modifier = modifier.padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = if(title.value.isNotEmpty() && subTasks.isNotEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary),
                enabled = title.value.isNotEmpty() && subTasks.isNotEmpty()
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Create Poll",
                    tint = if(title.value.isNotEmpty() && subTasks.isNotEmpty()) MaterialTheme.colorScheme.onPrimary else Color.White
                )
            }
        }
    }
}
class SubTaskTab (
    val id: Int,
    val removePollOption: (Int) -> Unit,
    var value: MutableState<String> = mutableStateOf(value = "")
){
    @Composable
    fun Create(
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { removePollOption(id) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Remove subtask", tint = MaterialTheme.colorScheme.primary)
            }
            InputField(value = value, placeholder = "Task $id"){value.value = it}

        }
    }
}
