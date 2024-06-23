package com.example.fleet

import android.app.Application

class FleetApplication : Application(){

    companion object{
        lateinit var fleetModule: FleetModule
    }

    override fun onCreate() {
        super.onCreate()
        fleetModule = FleetModule(this)
    }
}