package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
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
import com.example.fleet.presentation.components.cards.BaseCard
import com.example.fleet.presentation.components.cards.NotificationCard
import com.example.fleet.presentation.components.cards.PollCard
import com.example.fleet.presentation.components.cards.TaskCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

//Todo change all ID to some strong value
class NotificationViewModel (
    private val db: FleetDatabase = FleetApplication.fleetModule.fleetDatabase,
): ViewModel() {

    //
    private var _cards: MutableStateFlow<List<BaseCard>> = MutableStateFlow(mutableListOf())
    var cards = _cards.asStateFlow()

    //
    var isNotificationDialogShown by mutableStateOf(false)
        private set
    var isTaskDialogShown by mutableStateOf(false)
        private set
    var isPollDialogShown by mutableStateOf(false)
        private set

    init {
        tasksCollector()
        pollCollector()
        notificationCollector()
    }
    //Todo make this smarter
    private fun changePollOption(pollOptionSelected: PollOption, pollOptionUnselected: PollOption?){
        if (pollOptionUnselected == pollOptionSelected){
            pollOptionSelected.votes -= FleetApplication.fleetModule.tenantId
        }
        pollOptionSelected.votes += FleetApplication.fleetModule.tenantId
        viewModelScope.launch {
            db.pollOptionDao().upsert(
                pollOptionSelected
            )
        }
        if (pollOptionUnselected != null) {
            pollOptionUnselected.votes -= FleetApplication.fleetModule.tenantId
            viewModelScope.launch {
                db.pollOptionDao().upsert(
                    pollOptionUnselected
                )
            }
        }
    }

    private fun tasksCollector(){
        var tasks = emptyList<Task>()
        var subTasks = emptyList<SubTask>()
        //Todo this updates all tasks make it update only ones that have changed
        fun update(){
            _cards.update {prev ->
                prev.filterNot{ "Task" in it.id } + //Deletes all tasks from cards
                tasks.map { task ->
                    TaskCard(
                        task,
                        subTasks = subTasks.filter{it.taskId == task.id},
                        onTaskCompletion = {subTaskId -> subTasks.find {it.id == subTaskId}?.let { completeSubTask(it) } }
                    )
                }
            }
            _cards.update { prev -> prev.sortedByDescending {  it.createdAt } }
        }

        //
        viewModelScope.launch {
            db.taskDao().getByBuildingId(FleetApplication.fleetModule.building.value.id).collect {
                Log.i("NotificationViewModel", it.map{a-> a.id}.toString() + " Tasks")
                tasks = it
                update()
            }
        }
        viewModelScope.launch {
            db.subTaskDao().getByBuildingId(FleetApplication.fleetModule.building.value.id).collect {
                subTasks = it
                Log.i("NotificationViewModel", it.map{a ->a.id}.toString() + " SubTasks")

                update()
            }
        }
    }

    private fun pollCollector(){
        var polls = emptyList<Poll>()
        var pollOptions = emptyList<PollOption>()

        //Todo same with previous collector
        fun update(){
            _cards.update {prev ->
                prev.filterNot{ "Poll" in it.id } + //Deletes all polls from cards
                    polls.map { poll ->
                        PollCard(
                            poll,
                            pollOptions.filter { it.pollId == poll.id },
                            onPollOptionChange =  {poll1, poll2 -> changePollOption(poll1, poll2)}
                        )
                    }
            }
            _cards.update { prev -> prev.sortedByDescending {  it.createdAt } }
        }

        //
        viewModelScope.launch {
            db.pollDao().getByBuildingId(FleetApplication.fleetModule.building.value.id).collect {
                polls = it
                update()
            }
        }
        viewModelScope.launch {
            db.pollOptionDao().getByBuildingId(FleetApplication.fleetModule.building.value.id).collect {
                pollOptions = it
                update()
            }
        }
    }

    private fun notificationCollector(){
        //Todo again same - make update only ones that have changed
        viewModelScope.launch {
            db.notificationDao().getByBuildingId(FleetApplication.fleetModule.building.value.id).collect { notifications ->
                _cards.update {prev -> prev.filterNot{"Notification" in it.id } + notifications.map { NotificationCard(it) }}
                _cards.update { prev -> prev.sortedByDescending { it.createdAt  } }
            }
        }
    }

    //
    private fun completeSubTask(subTask: SubTask){
        subTask.completed = !subTask.completed
        viewModelScope.launch { db.subTaskDao().upsert(subTask)}
    }

    //
    fun createNotification(title: String, text: String){
        viewModelScope.launch {
            db.notificationDao().upsert(
                Notification(
                    buildingId = FleetApplication.fleetModule.building.value.id,
                    title = title,
                    text = text,
                    imageResId = null,
                    creatorId = FleetApplication.fleetModule.tenantId
                )
            )
        }
    }

    //
    fun createPoll(title: String, options: List<String>, endDate: Int){
        val poll = Poll(
            id = UUID.randomUUID().toString(),
            creatorId = FleetApplication.fleetModule.tenantId,
            buildingId = FleetApplication.fleetModule.building.value.id,
            title = title,
            pollType = PollType.SINGLE_CHOICE,
            endDate = LocalDate.now().plusDays(endDate.toLong())
        )

        viewModelScope.launch {
            db.pollDao().upsert(poll)
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

    //
    fun createTask(title: String, subTasks: List<String>){
        val task = Task(
            id = UUID.randomUUID().toString(),
            creatorId = FleetApplication.fleetModule.tenantId,
            buildingId = FleetApplication.fleetModule.building.value.id,
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
    //
    fun toggleNotificationDialog(){isNotificationDialogShown = !isNotificationDialogShown}
    fun toggleTaskDialog(){isTaskDialogShown = !isTaskDialogShown}
    fun togglePollDialog(){isPollDialogShown = !isPollDialogShown}
    fun scrollToLastNotification(lazyState: LazyListState){
        //Make it scroll to the bottom not top of the last item
        viewModelScope.launch { lazyState.scrollToItem(0) }
    }
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














