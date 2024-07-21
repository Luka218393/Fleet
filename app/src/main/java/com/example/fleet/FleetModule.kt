package com.example.fleet

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.daos.TenantIdAndName
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class FleetModule(
    val context: Context
): ViewModel() {

    val fleetDatabase: FleetDatabase = FleetDatabase.getDatabase(context)

    init{
        //seed(fleetDatabase)

        viewModelScope.launch {
            fleetDatabase.settingsDao().get().collect{
                settings.value = it
                //Todo this makes app recompose twice on initialisation
                appColor = Color(it.appColor.toULong())
                buildingId = it.buildingId
                tenantId = it.tenantId
                apartmentId = it.apartmentId
                theme = it.theme
                font = it.font
                showNotifications = it.showNotifications
                showAnimation = it.showAnimation
                language = it.language
            }
        }
    }
    private var settings: MutableState<Settings> = mutableStateOf(runBlocking { fleetDatabase.settingsDao().get().first() })

    var appColor by mutableStateOf(Color(settings.value.appColor.toULong()))
    var buildingId by mutableIntStateOf(settings.value.buildingId)
    var apartmentId by mutableIntStateOf(settings.value.apartmentId)
    var tenantId by mutableIntStateOf(settings.value.tenantId)
    var theme by mutableStateOf(settings.value.theme)
    var font by mutableStateOf(settings.value.font)
    var showAnimation by mutableStateOf(settings.value.showAnimation)
    var showNotifications by mutableStateOf(settings.value.showNotifications)
    var language by mutableStateOf(settings.value.language)

    fun upsertSettingsColor(newColor: Color){
        runBlocking { fleetDatabase.settingsDao().upsert(settings.value.copy(appColor = newColor.value.toString())) }
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