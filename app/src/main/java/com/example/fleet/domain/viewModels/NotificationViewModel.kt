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
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.presentation.fragments.BaseCard
import com.example.fleet.presentation.fragments.NotificationCard
import com.example.fleet.presentation.fragments.PollCard
import com.example.fleet.presentation.fragments.TaskCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date

class NotificationViewModel (
    val db: FleetDatabase,
    var settings: MutableStateFlow<Settings>,
): ViewModel() {

    private var _cards: MutableStateFlow<List<BaseCard?>> = MutableStateFlow(mutableListOf())
    var cards = _cards.asStateFlow()

    var isNotificationDialogShown by mutableStateOf(false)
        private set
    var isTaskDialogShown by mutableStateOf(false)
        private set
    var isPollDialogShown by mutableStateOf(false)
        private set


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

    fun toggleNotificationDialog(){isNotificationDialogShown = !isNotificationDialogShown}
    fun toggleTaskDialog(){isTaskDialogShown = !isTaskDialogShown}
    fun togglePollDialog(){isPollDialogShown = !isPollDialogShown}

    private fun insertTaskToCards(){
        viewModelScope.launch {
            db.taskDao().getByBuildingId(settings.value.buildingId).collect{task ->
                _cards.update {prev -> prev.filterNot{"Task" in (it?.id ?: "") } + task.map{ TaskCard(it) } }
                _cards.update { prev -> prev.sortedByDescending { it?.createdAt ?: Date(1, 1, 1) } }
            }
        }
    }

    private fun insertPollToCards(){
        viewModelScope.launch {
            db.pollDao().getByBuildingId(settings.value.buildingId).collect { polls ->
                db.pollOptionDao().getAll().collect { pollOptions ->
                        //Todo make so that poll cannot be created without any options and rhan remove if statement
                        _cards.update {prev -> prev.filterNot{ "Poll" in (it?.id ?: "") } +
                            polls.map { poll -> if (pollOptions.any { it.pollId == poll.id })  PollCard( poll, pollOptions.filter { it.pollId == poll.id }, onPollOptionChange =  {poll1, poll2 -> changePollOption(poll1, poll2)}) else null}
                        }
                    _cards.update { prev -> prev.sortedByDescending {  it?.createdAt ?: Date(1, 1, 1)  } }
                }
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


    init {
        Log.i("NotificationViewModel", "NotificationViewModel init")
        runBlocking{
            insertTaskToCards()
            insertPollToCards()
            insertNotificationToCards()
        }
    }
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














