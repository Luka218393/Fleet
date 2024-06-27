package com.example.fleet.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DisplayViewModel(
    private val db: FleetDatabase,
    private val settings: MutableStateFlow<Settings>
): ViewModel() {

    private var _displayTenant: MutableStateFlow<Tenant?> = MutableStateFlow(null)
    var displayTenant = _displayTenant.asStateFlow()
    fun getTenantById(id: Int) {
        viewModelScope.launch {
            _displayTenant.value = db.tenantDao().getById(id).first()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DisplayViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayViewModel::class.java)) {
            return DisplayViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
