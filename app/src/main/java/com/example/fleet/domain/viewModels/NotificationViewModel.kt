package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.R
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.cards
import com.example.fleet.data.pollCards
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.data.settings1
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Task
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.fragments.BaseCard
import com.example.fleet.presentation.fragments.NotificationCard
import com.example.fleet.presentation.fragments.PollCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale.filter

class NotificationViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    var cards: List<BaseCard>

    private var tasks: Flow<Task>

    private var notifications: Flow<List<Notification>>
    private var notificationCards: MutableList<NotificationCard>
    private var polls: Flow<List<Poll>>
    private var pollOptions: Flow<List<PollOption>>
    private var pollCards: MutableList<PollCard>


    init {
        runBlocking {//Todo make this smarter

            notifications = db.notificationDao().getByBuildingId(settings.value.buildingId)
            polls = db.pollDao().getByBuildingId(settings.value.buildingId)
            pollOptions = db.pollOptionDao().getAll()
            tasks = db.taskDao().getByBuildingId(settings.value.buildingId)
            pollCards = mutableListOf()
            for (poll in polls.first()){pollCards.add(PollCard(poll, pollOptions.first().filter { pollOption -> pollOption.pollId == poll.id }))}

            Log.i("NotificationViewModel", notifications.first()[0].iconResId.toString())
            notifications.first()[0].iconResId = Icons.Default.Star

            notificationCards = mutableListOf()
            for (notification in notifications.first()){ notificationCards.add(NotificationCard(notification))}

            cards = pollCards + notificationCards
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
