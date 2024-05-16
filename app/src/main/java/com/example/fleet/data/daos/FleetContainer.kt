package com.example.fleet.data.daos

import android.content.Context

interface AppContainer {
    val pollOptionRepository: PollOptionRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val pollOptionRepository: PollOptionRepository by lazy {
        OfflinePollOptionRepository(FleetDatabase.getDatabase(context).pollOptionDao())
    }
}