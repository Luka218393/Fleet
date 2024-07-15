package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Enums.PollType
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.domain.Models.Task
import com.example.fleet.presentation.fragments.cards.BaseCard
import com.example.fleet.presentation.fragments.cards.NotificationCard
import com.example.fleet.presentation.fragments.cards.PollCard
import com.example.fleet.presentation.fragments.cards.TaskCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.Date
import kotlin.random.Random

class NotificationViewModel (
    private val db: FleetDatabase = FleetApplication.fleetModule.fleetDatabase,
): ViewModel() {

    private var _cards: MutableStateFlow<List<BaseCard?>> = MutableStateFlow(mutableListOf())
    var cards = _cards.asStateFlow()

    var isNotificationDialogShown by mutableStateOf(false)
        private set
    var isTaskDialogShown by mutableStateOf(false)
        private set
    var isPollDialogShown by mutableStateOf(false)
        private set

    init {
        runBlocking{
            insertTaskToCards()
            insertPollToCards()
            insertNotificationToCards()
        }
    }
    //Todo make this smarter
    private fun changePollOption(pollOptionSelected: PollOption, pollOptionUnselected: PollOption?){
        if (pollOptionUnselected == pollOptionSelected){
            pollOptionSelected.votes -=1
        }
        pollOptionSelected.votes += 1
        viewModelScope.launch {
            db.pollOptionDao().upsert(
                pollOptionSelected
            )
        }
        if (pollOptionUnselected != null) {
            pollOptionUnselected.votes -= 1
            viewModelScope.launch {
                db.pollOptionDao().upsert(
                    pollOptionUnselected
                )
            }
        }

    }



    private fun insertTaskToCards(){
        var tasks = emptyList<Task>()
        var subTasks = emptyList<SubTask>()

        fun update(){
            _cards.update {prev ->
                prev.filterNot{ "Task" in (it?.id ?: "") } + //Deletes all tasks from cards
                    tasks.map { task ->
                        TaskCard(
                            task,
                            subTasks = subTasks.filter{it.taskId == task.id},
                            onTaskCompletion = {subTaskId -> subTasks.find {it.id == subTaskId}?.let { completeSubTask(it) } }
                        )
                    }
            }
            _cards.update { prev -> prev.sortedByDescending {  it?.createdAt ?: Date(1, 1, 1)  } }
        }
        viewModelScope.launch {
            db.taskDao().getAll().collect {
                tasks = it
                update()
            }
        }

        viewModelScope.launch {
            db.subTaskDao().getAll().collect {
                subTasks = it
                update()
            }
        }
    }

    //Todo totally remodel poll creation
    // i don't have nerves to do this anymore
    private fun insertPollToCards(){
        var polls = emptyList<Poll>()
        var pollOptions = emptyList<PollOption>()
        fun update(){
            _cards.update {prev ->
                prev.filterNot{ "Poll" in (it?.id ?: "") } + //Deletes all polls from cards
                    polls.map { poll ->
                        PollCard(
                            poll, pollOptions.filter { it.pollId == poll.id },
                            showResult = true,
                            onPollOptionChange =  {poll1, poll2 -> changePollOption(poll1, poll2)}
                        )
                    }
            }
            _cards.update { prev -> prev.sortedByDescending {  it?.createdAt ?: Date(1, 1, 1)  } }
        }
        viewModelScope.launch {
            db.pollOptionDao().getAll().collect {
                //Todo make so that poll cannot be created without any options and than remove if statement
                pollOptions = it
                update()
            }
        }

        viewModelScope.launch {
            db.pollDao().getAll().collect {
                polls = it
                update()
            }
        }
    }
    //Todo make notifications use only ones that have building id (Same with tasks and polls)
    private fun insertNotificationToCards(){
        viewModelScope.launch {
            db.notificationDao().getAll().collect { notifications ->
                _cards.update {prev -> prev.filterNot{"Notification" in (it?.id ?: "") } + notifications.map { NotificationCard(it) }}
                _cards.update { prev -> prev.sortedByDescending { it?.createdAt ?: Date(1, 1, 1)  } }
                Log.i("NotificationViewModel", notifications.toString() + FleetApplication.fleetModule.settings.value.buildingId.toString())
            }
        }
    }

    private fun completeSubTask(subTask: SubTask){
        subTask.completed = !subTask.completed
        runBlocking { db.subTaskDao().upsert(subTask)}
    }

    fun createNotification(title: String, text: String){
        viewModelScope.launch {
            db.notificationDao().upsert(
                Notification(
                    buildingId = FleetApplication.fleetModule.settings.value.buildingId,
                    title = title,
                    text = text,
                    imageResId = null,
                    iconResId = Icons.Default.Favorite,
                    creatorId = FleetApplication.fleetModule.settings.value.tenantId
                )
            )
        }
    }

    fun createPoll(title: String, options: List<String>, endDate: Int){
        val poll = Poll(
            id = Random.nextLong(999999999999999999).toInt(),
            creatorId = FleetApplication.fleetModule.settings.value.tenantId,
            buildingId = FleetApplication.fleetModule.settings.value.buildingId,
            title = title,
            pollType = PollType.SINGLE_CHOICE,
            voteEndDate = LocalDate.now().plusDays(endDate.toLong())
        )
        Log.i("NotificationViewModel",poll.voteEndDate.toString())
        viewModelScope.launch {
            db.pollDao().upsert(
                poll
            )
            for (i in options) {
                db.pollOptionDao().upsert(
                    PollOption(
                        value = i,
                        pollId = poll.id
                    )
                )
            }
        }
    }

    fun createTask(title: String, subTasks: List<String>){
        val task = Task(
            id = Random.nextLong(999999999999999999).toInt(),
            creatorId = FleetApplication.fleetModule.settings.value.tenantId,
            buildingId = FleetApplication.fleetModule.settings.value.buildingId,
            title = title,
        )
        viewModelScope.launch {
            db.taskDao().upsert(task)
            for (i in subTasks) {
                db.subTaskDao().upsert(
                    SubTask(
                        text = i,
                        taskId = task.id,
                    )
                )
            }
        }
    }
    fun toggleNotificationDialog(){isNotificationDialogShown = !isNotificationDialogShown}
    fun toggleTaskDialog(){isTaskDialogShown = !isTaskDialogShown}
    fun togglePollDialog(){isPollDialogShown = !isPollDialogShown}
}

@Suppress("UNCHECKED_CAST")
class NotificationViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}














