package com.example.fleet

import android.content.Context
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.flow.Flow


class FleetModule(
    private val context: Context
) {
    val fleetDatabase: FleetDatabase = FleetDatabase.getDatabase(context)
    val settings: Flow<Settings> = fleetDatabase.settingsDao().get()
}