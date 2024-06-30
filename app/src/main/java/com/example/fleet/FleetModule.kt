package com.example.fleet

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelStore
import com.example.fleet.data.FleetDatabase
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
    // this crahses the app on installation and first creation
    var appColor: Color = Color(settings.value.appColor.toULong())


}