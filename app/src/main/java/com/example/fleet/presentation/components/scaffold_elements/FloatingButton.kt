package com.example.fleet.presentation.components.scaffold_elements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Immutable
data class FloatingButtonState(
    val toggleNotificationDialog: () -> Unit,
    val toggleTaskDialog: () -> Unit,
    val togglePollDialog:() -> Unit
)

@Stable
@Composable
fun FloatingButton(
    floatingButtonState: FloatingButtonState
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End,
        ){

        AnimatedVisibility(
            visible = expanded,
        ) {
            Column {
                IconChip(icon = Icons.Default.Add, text = "Event" ) {
                    floatingButtonState.toggleNotificationDialog()
                }
                IconChip(icon = Icons.Default.Add, text = "Poll" ) {
                    floatingButtonState.togglePollDialog()
                }
                IconChip(icon = Icons.Default.Add, text = "Task" ) {
                    floatingButtonState.toggleTaskDialog()
                }
            }
        }
        FloatingActionButton(
            onClick = { expanded = !expanded },
            shape = CircleShape,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier.size(64.dp)
        ) {
            AnimatedContent(targetState = expanded,
                label = "FAB animation",
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                }) {
                if (it){
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Stable
@Composable
internal fun IconChip(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp))
            .size(100.dp, 28.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable { onClick() }
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = RoundedCornerShape(10.dp) )
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Composable
fun SimpleFloatingButton(
    onclick: () -> Unit,
    icon: ImageVector
) {
    FloatingActionButton(
        onClick = onclick,
        shape = CircleShape,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier.size(64.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "New Chat",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}