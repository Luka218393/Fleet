package com.example.fleet.presentation.components.cards

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.domain.Models.Task


//todo make result tint in color when finished (make color disabled)
class TaskCard (
    private val task: Task,
    private val subTasks: List<SubTask>,
    private val onTaskCompletion: (String) -> Unit,//Todo Make mutable
): BaseCard(task.createdAt, task.creatorId,"Task id:" + task.id) {
    //
    @Composable
    override fun Content() {
        Column {

            Title(title = task.title)

            for (subTask in subTasks) {
                SubTaskDisplay(subTask, onTaskCompletion)
            }
        }
    }
}

//
@Composable
fun SubTaskDisplay(
    subTask: SubTask,
    onTaskCompletion: (String) -> Unit,
    modifier: Modifier = Modifier,
){

   Column {

       Row (
           modifier = modifier.height(50.dp),
           verticalAlignment = Alignment.CenterVertically
       ){
           Text(text = subTask.text,
               modifier = modifier
                   .padding(12.dp)
                   .weight(8f),
               color = MaterialTheme.colorScheme.onSecondaryContainer,
               style = MaterialTheme.typography.bodyMedium
           )
           //TODO Make this draggable

           VerticalDivider(
               modifier = modifier.fillMaxHeight(),
               thickness = 0.4.dp,
               color = MaterialTheme.colorScheme.primary
           )
           Box(
               modifier = modifier
                   .weight(2f)
                   .fillMaxSize()
                   ,
               contentAlignment = Alignment.Center
           ){
               IconButton(
                   onClick = { onTaskCompletion(subTask.id) }
               ) {
                   AnimatedContent(targetState = subTask.completed,
                       modifier = modifier.size(32.dp),
                       label = "Task icon animation",
                       transitionSpec = {
                           fadeIn() togetherWith fadeOut()
                       }) {
                       if (it){
                           Icon(
                               painter = painterResource(id = R.drawable.check_icon) ,
                               contentDescription = "icon",
                               tint = MaterialTheme.colorScheme.primary,
                           )
                       }
                       else{
                           Icon(
                               painter = painterResource(id = R.drawable.arrow_left_icon),
                               contentDescription = "icon",
                               tint =  MaterialTheme.colorScheme.secondary ,
                           )
                       }
                   }
               }
           }
       }
       HorizontalDivider(
           modifier = modifier
               .fillMaxWidth(),
           thickness = 0.4.dp,
           color = MaterialTheme.colorScheme.primary
       )
   }
}

