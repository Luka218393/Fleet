package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.settings1
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Task
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.presentation.fragments.BaseCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotificationViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    lateinit var cards: Flow<BaseCard>
    lateinit var notifications: Flow<Notification>
    lateinit var polls: Flow<Poll>
    //lateinit var pollOptions: Flow<PollOption>
    lateinit var tasks: Flow<Task>

    init {
        runBlocking {//Todo make this smarter
            notifications = db.notificationDao().getByBuildingId(settings.value.buildingId)
            polls = db.pollDao().getByBuildingId(settings.value.buildingId)
            //pollOptions = db.notificationDao().getByBuildingId(settings.value.buildingId)
            tasks = db.taskDao().getByBuildingId(settings.value.buildingId)

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
