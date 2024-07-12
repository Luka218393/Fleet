package com.example.fleet

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.daos.TenantIdAndName
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)

class FleetModule(
    val context: Context
): ViewModel() {

    val fleetDatabase: FleetDatabase = FleetDatabase.getDatabase(context)
    var settings: MutableState<Settings> = mutableStateOf(Settings())

    init{
        //seed(fleetDatabase)
        viewModelScope.launch {
            fleetDatabase.settingsDao().get().collect{
                //The recomposition probably has something to do with Primary key of Settings, That is why i have to change it two times
                settings.value = Settings()
                settings.value = it
            }
        }
    }
    private var tenantNames: List<TenantIdAndName> = runBlocking { fleetDatabase.tenantDao().getTenantsIdAndName().first()}
    fun getTenantNameAndSurname(id: Int): String? = tenantNames.find { it.id == id }?.let { it.name + " " + it.surname }
}

@Suppress("UNCHECKED_CAST")
class FleetModuleFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FleetModule::class.java)) {
            return FleetModule(context = context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}