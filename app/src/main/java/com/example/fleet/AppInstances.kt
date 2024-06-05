package com.example.fleet

import androidx.lifecycle.ViewModelProvider
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.cards
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.MainViewModel
import com.example.fleet.domain.viewModels.MainViewModelFactory
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.activities.NotificationActivity

class AppInstances (
    notificationViewModel: NotificationViewModel,
    /*Todo add remaining activities*/

){
    private val navigation = Navigation(this)
    val notificationActivity = NotificationActivity(cards = cards, viewModel = notificationViewModel, navigation = navigation)

}