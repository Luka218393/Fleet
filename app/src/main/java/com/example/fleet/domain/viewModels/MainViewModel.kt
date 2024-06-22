package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainViewModel (
    var db: FleetDatabase
): ViewModel() {

    var settings: MutableStateFlow<Settings>
    var tenant: Flow<Tenant>
    var apartment: Flow<Tenant>
    var building: Flow<Tenant>

    /*
        tenant = flow<Tenant>{emit it}
    */
    init {
        runBlocking {//Todo make this smarter
            settings = MutableStateFlow(db.settingsDao().get().first())
            tenant = db.tenantDao().getById(settings.value.tenantId)
            apartment = db.tenantDao().getById(settings.value.apartmentId)
            building = db.tenantDao().getById(settings.value.buildingId)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val db: FleetDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
