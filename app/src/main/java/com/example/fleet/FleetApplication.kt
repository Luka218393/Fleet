package com.example.fleet
/*
import android.app.Application
import com.example.fleet.data.daos.AppContainer
import com.example.fleet.data.daos.AppDataContainer
import com.example.fleet.data.pollOptions
import kotlinx.coroutines.runBlocking

class FleetApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    private lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        runBlocking {
            container.pollOptionRepository.insertPollOption(pollOption = pollOptions[2])
        }
    }
}
*/