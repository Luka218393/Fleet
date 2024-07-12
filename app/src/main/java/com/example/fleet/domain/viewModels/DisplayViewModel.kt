package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DisplayViewModel(
    private val db: FleetDatabase,
): ViewModel() {
    fun getTenantById(id: Int) = runBlocking { db.tenantDao().getById(id).first() }
}

@Suppress("UNCHECKED_CAST")
class DisplayViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayViewModel::class.java)) {
            return DisplayViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
