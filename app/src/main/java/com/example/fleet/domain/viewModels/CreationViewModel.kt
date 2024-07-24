package com.example.fleet.domain.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.launch
import kotlin.random.Random

class CreationViewModel(
    private val db: FleetDatabase,
): ViewModel() {

    var tenantId by mutableStateOf<Int?>(null)
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var email by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var gender by mutableStateOf("")
    var birthday by mutableStateOf("")
    var profession by mutableStateOf("")
    var description by mutableStateOf("")





    fun createTenant(){
        tenantId = Random.nextInt(Int.MAX_VALUE)
        viewModelScope.launch {
            db.tenantDao().upsert(
                Tenant(
                    tenantId!!,
                    2,
                    name,
                    surname,
                    phoneNumber,
                    email,
                    gender,
                    null,
                    null,
                    profession,
                    description,
                )
            )
        }
    }

    fun createSettings(){
        viewModelScope.launch {
            db.settingsDao().upsert(
                Settings(

                )
            )
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CreationViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreationViewModel::class.java)) {
            return SettingsViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}