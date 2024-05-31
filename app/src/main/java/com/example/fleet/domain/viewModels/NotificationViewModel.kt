package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import com.example.fleet.data.FleetDatabase

class NotificationViewModel (
    private val db: FleetDatabase
): ViewModel() {
    fun getNotifications() = db.notificationDao()
}