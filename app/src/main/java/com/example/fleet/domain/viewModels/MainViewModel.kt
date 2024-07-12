package com.example.fleet.domain.viewModels

/*
class MainViewModel(
    private val db: FleetDatabase,
    private val settings: MutableStateFlow<Settings>
): ViewModel() {
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(FleetApplication.fleetModule.fleetDatabase,  FleetApplication.fleetModule.settings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/