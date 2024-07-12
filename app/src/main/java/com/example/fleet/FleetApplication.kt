package com.example.fleet

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

class FleetApplication : Application(){

    companion object{
        lateinit var fleetModule: FleetModule
        val viewModelStore: ViewModelStore = ViewModelStore()//Todo maybe make this bind to MainActivity in future (this is used in viewModels)

    }

    override fun onCreate() {
        super.onCreate()
        fleetModule = ViewModelProvider(viewModelStore, FleetModuleFactory(this))[FleetModule::class.java]
    }
}