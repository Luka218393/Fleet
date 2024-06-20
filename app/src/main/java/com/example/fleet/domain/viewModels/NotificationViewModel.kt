package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Task
import com.example.fleet.presentation.fragments.BaseCard
import com.example.fleet.presentation.fragments.NotificationCard
import com.example.fleet.presentation.fragments.PollCard
import com.example.fleet.presentation.fragments.TaskCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NotificationViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    var cards: List<BaseCard>

    private var tasks: Flow<List<Task>>
    private var taskCards: MutableList<TaskCard> = mutableListOf()
    private var notifications: Flow<List<Notification>>
    private var notificationCards: MutableList<NotificationCard> = mutableListOf()
    private var polls: Flow<List<Poll>>
    private var pollOptions: Flow<List<PollOption>>
    private var pollCards: MutableList<PollCard> = mutableListOf()


    init {
        runBlocking {//Todo make this smarter & remove runBlocking

            Log.i("NotificationViewModel", "NotificationViewModel init")

            notifications = db.notificationDao().getByBuildingId(settings.value.buildingId)

            polls = db.pollDao().getByBuildingId(settings.value.buildingId)
            pollOptions = db.pollOptionDao().getAll()

            tasks = db.taskDao().getByBuildingId(settings.value.buildingId)

            for (poll in polls.first()){pollCards.add(PollCard(poll, pollOptions.first().filter { pollOption -> pollOption.pollId == poll.id }))}
            for (notification in notifications.first()){ notificationCards.add(NotificationCard(notification))}

            /*TODO Checkbutton isn't updating*/
            for (task in tasks.first()){
                taskCards.add(TaskCard(
                    task = task,
                    onCheckboxChange = {task.completed = it; runBlocking {db.taskDao().upsert(task)}})
                )
            }
            /*Todo sort by date*/
            cards = taskCards + notificationCards + pollCards
            cards.sortedBy { card -> card.createdAt  }
        }
    }

    fun changeNotification(notification1: Notification){//Todo make this smarter & remove runBlocking
        runBlocking {
            db.notificationDao().upsert(notification1)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class NotificationViewModelFactory(private val db: FleetDatabase, private val settings: MutableStateFlow<Settings>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(db, settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
