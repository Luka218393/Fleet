package com.example.fleet.domain.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase

class SettingsViewModel(
    private val db: FleetDatabase,
): ViewModel() {
     var showColorSelector by mutableStateOf(false)
        private set

    fun toggleColorPalette(){showColorSelector = !showColorSelector}


    //Todo this works but badly
    fun changeSettingsColor(color: Color, isDarkTheme: Boolean){

        FleetApplication.fleetModule.upsertSettingsColor(color)
    }
}

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
