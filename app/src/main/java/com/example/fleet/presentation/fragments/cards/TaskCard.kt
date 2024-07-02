package com.example.fleet.presentation.fragments.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fleet.data.subTasks
import com.example.fleet.data.tasks
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.domain.Models.Task


//TODO add task result card

//Todo create TaskSetCard
//Todo and make finishable
//todo make result hidable
class TaskCard (
    private val task: Task,
    private val subTasks: List<SubTask>,
    private val onTaskCompletion: (Int) -> Unit,//Todo Make muatble
    private val modifier: Modifier = Modifier
): BaseCard(task.createdAt, task.creatorId,"Task id:" + task.id){
    @Composable
    override fun Content() {
        Column{

            TaskDisplay()

            for (subTask in subTasks) {
                SubTaskDisplay(subTask)
            }

        }
    }
    @Composable
    fun TaskDisplay(){
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
    }

    @Composable
    fun SubTaskDisplay(
        subTask: SubTask
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
                    style = MaterialTheme.typography.bodyMedium
                )
                VerticalDivider(
                    modifier = modifier.fillMaxHeight(),
                    thickness = 0.4.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Box(
                    modifier = modifier
                        .weight(2f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    IconButton(
                        onClick = { onTaskCompletion(subTask.id) }
                    ) {

                        Icon(//TODO Make this invisible
                            imageVector = if(subTask.completed) Icons.Default.Check else Icons.Default.Clear,
                            contentDescription = "icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = modifier
                                .size(32.dp)
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = modifier
                    .fillMaxWidth(),
                //.padding(bottom = 4.dp),
                thickness = 0.4.dp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}







@Composable
@Preview
fun TaskCardPreview(){
    TaskCard(tasks[0], subTasks.subList(1,3),{}).Content()
}

