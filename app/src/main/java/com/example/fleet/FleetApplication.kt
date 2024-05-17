package com.example.fleet

import android.app.Application
import com.example.fleet.data.daos.AppContainer
import com.example.fleet.data.daos.AppDataContainer

class FleetApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    private lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}