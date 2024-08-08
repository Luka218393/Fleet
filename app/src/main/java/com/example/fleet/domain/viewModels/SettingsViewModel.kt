package com.example.fleet.domain.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val db: FleetDatabase,
): ViewModel() {
     var showColorSelector by mutableStateOf(false)
        private set

    fun toggleColorPalette(){showColorSelector = !showColorSelector}

    fun changeSettingsColor(newColor: Color) = viewModelScope.launch(Dispatchers.IO) { db.settingsDao().upsert(FleetApplication.fleetModule.settings.value.copy(appColor = newColor.value.toString())) }

    fun changeUserId(newId: String) = viewModelScope.launch(Dispatchers.IO) { db.settingsDao().upsert(FleetApplication.fleetModule.settings.value.copy(tenantId = newId)) }

    fun toggleImmersiveMode() {
        viewModelScope.launch(Dispatchers.IO) {
            db.settingsDao().upsert(
                FleetApplication.fleetModule.settings.value.copy(
                    immersiveMode = !FleetApplication.fleetModule.settings.value.immersiveMode
                )
            )
        }
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
