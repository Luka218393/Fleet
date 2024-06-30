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
import com.example.fleet.domain.Models.Settings
import com.example.fleet.presentation.ui.theme.DarkScheme
import com.example.fleet.presentation.ui.theme.LightScheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsViewModel(
    private val db: FleetDatabase,
    private val settings: MutableStateFlow<Settings>
): ViewModel() {
     var showColorSelector by mutableStateOf(false)
        private set

    fun toggleColorPalette(){showColorSelector = !showColorSelector}


    //Todo this works but badly
    fun changeSettingsColor(color: Color, isDarkTheme: Boolean){
        settings.value.appColor = color.value.toString()
        if(isDarkTheme) {
            DarkScheme.value = DarkScheme.value.copy(secondary = color)
        }
        else {
            LightScheme.value = LightScheme.value.copy(secondary = color)
        }
        runBlocking{db.settingsDao().upsert(settings = settings.value)}
        viewModelScope.launch {
            db.settingsDao().get().collect{
                    FleetApplication.fleetModule.appColor = Color(it.appColor.toULong())
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
