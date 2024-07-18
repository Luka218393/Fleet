package com.example.fleet.presentation.fragments.cards.card_dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fleet.presentation.fragments.input_fields.InputField

//Todo make all of theese functions objects
@Composable
fun NotificationDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val title = remember { mutableStateOf("") }
    val text = remember { mutableStateOf("") }


    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Column (
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = "Create Task",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Card {
                    Column{
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
                            InputField(title, "Title"){title.value = it}
                            //https://developer.android.com/develop/ui/compose/text/user-input
                        }

                        HorizontalDivider(
                            modifier = modifier.fillMaxWidth(),
                            thickness = 0.4.dp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Column (
                            modifier = modifier.padding(12.dp)
                        ){
                            InputField(text, "Text", 10){text.value = it}
                        }

                    }
                }
                IconButton(
                    onClick = {onConfirm(title.value, text.value)},
                    modifier = modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = if (title.value.isNotEmpty() && text.value.isNotEmpty()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary),
                    enabled = title.value.isNotEmpty() && text.value.isNotEmpty()
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Create Notification")
                }
            }

        }
    )
}



@Preview
@Composable
fun NotificationCreationPreview(){
    NotificationDialog({},{ a, b ->})
}


