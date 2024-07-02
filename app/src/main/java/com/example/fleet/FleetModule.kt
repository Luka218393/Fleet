package com.example.fleet

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelStore
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.daos.TenantIdAndName
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class FleetModule(
    val context: Context
) {

    val fleetDatabase: FleetDatabase = FleetDatabase.getDatabase(context)

    init{
        //seed(fleetDatabase)
    }

    val settings: MutableStateFlow<Settings> = runBlocking {MutableStateFlow(fleetDatabase.settingsDao().get().first())}
    val viewModelStore: ViewModelStore = ViewModelStore()//Todo maybe make this bind to MainActivity in future (this is used in viewModels)
    //Todo appColor isnt applied optimally but it is probably faster
    var appColor: Color = Color(settings.value.appColor.toULong())
    private var tenantNames: List<TenantIdAndName> = runBlocking { fleetDatabase.tenantDao().getTenantsIdAndName().first()}
    fun getTenantNameAndSurname(id: Int): String? = tenantNames.find { it.id == id }?.let { it.name + " " + it.surname }
}