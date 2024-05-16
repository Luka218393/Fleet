package com.example.fleet.data.daos

import com.example.fleet.domain.Models.PollOption
import kotlinx.coroutines.flow.Flow

interface PollOptionRepository {
    fun getAllPollOptionStream(): Flow<List<PollOption>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getPollOptionStream(id: Int): Flow<PollOption?>

    /**
     * Insert item in the data source
     */
    suspend fun insertPollOption(pollOption: PollOption)

    /**
     * Delete item from the data source
     */
    suspend fun deletePollOption(pollOption: PollOption)

    /**
     * Update item in the data source
     */
    suspend fun updatePollOption(pollOption: PollOption)
}