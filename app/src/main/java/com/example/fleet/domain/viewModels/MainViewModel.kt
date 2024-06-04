package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.settings1
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel (
    var db: FleetDatabase
): ViewModel() {

    lateinit var settings: MutableStateFlow<Settings>
    lateinit var tenant: Flow<Tenant>
    lateinit var apartment: Flow<Tenant>
    lateinit var building: Flow<Tenant>
    init {
        Log.i("aaa", "Pokemoni1")

        runBlocking {//Todo make this smarter
            Log.i("aaa", "Pokemoni2")
            settings = MutableStateFlow(db.settingsDao().get().first())
            Log.i("aaa", settings.toString())
            /*tenant = db.tenantDao().getById(settings..tenantId)
            apartment = db.tenantDao().getById(settings.apartmentId)
            building = db.tenantDao().getById(settings.buildingId)*/
        }
    }
}

class MainViewModelFactory(private val db: FleetDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
