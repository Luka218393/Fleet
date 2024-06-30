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
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.presentation.fragments.cards.BaseCard
import com.example.fleet.presentation.fragments.cards.NotificationCard
import com.example.fleet.presentation.fragments.cards.PollCard
import com.example.fleet.presentation.fragments.cards.TaskCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date
import kotlin.random.Random

class NotificationViewModel (
    private val db: FleetDatabase = FleetApplication.fleetModule.fleetDatabase,
    private val settings: MutableStateFlow<Settings> = FleetApplication.fleetModule.settings
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



    fun createPoll(title: String, options: List<String>){
        val poll = Poll(
            id = Random.nextLong(999999999999999999).toInt(),
            creatorId = settings.value.tenantId,
            buildingId = settings.value.buildingId,
            title = title,
            pollType = PollType.SINGLE_CHOICE
        )
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
        viewModelScope.launch {
            db.taskDao().getByBuildingId(settings.value.buildingId).collect{tasks ->
                db.subTaskDao().getAll().collect{ subTasks ->
                    _cards.update {prev -> prev.filterNot{"Task" in (it?.id ?: "") } + tasks.map{ task -> TaskCard(task, subTasks = subTasks.filter{it.taskId == task.id}, {subTaskId -> subTasks.find {it.id == subTaskId}?.let { completeSubTask(it) } }) } }
                    _cards.update { prev -> prev.sortedByDescending { it?.createdAt ?: Date(1, 1, 1) } }
                }
            }
        }
    }

    private fun completeSubTask(subTask: SubTask){
        subTask.completed = !subTask.completed
        runBlocking { db.subTaskDao().upsert(subTask)}
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
                Log.i("NotificationViewModel", "Poll collection")
                polls = it
                update()
            }
        }
    }

    private fun insertNotificationToCards(){
        viewModelScope.launch {
            db.notificationDao().getByBuildingId(settings.value.buildingId).collect { notifications ->
                _cards.update {prev -> prev.filterNot{"Notification" in (it?.id ?: "") } + notifications.map { NotificationCard(it) }}
                _cards.update { prev -> prev.sortedByDescending { it?.createdAt ?: Date(1, 1, 1)  } }

            }
        }
    }



    fun createNotification(title: String, text: String){
        viewModelScope.launch {
            db.notificationDao().upsert(
                Notification(
                    buildingId = settings.value.buildingId,
                    title = title,
                    text = text,
                    imageResId = null,
                    iconResId = Icons.Default.Favorite,
                    creatorId = settings.value.tenantId
                )
            )
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
            return NotificationViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}














