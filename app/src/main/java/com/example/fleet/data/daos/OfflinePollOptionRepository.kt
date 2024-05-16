package com.example.fleet.data.daos

import com.example.fleet.domain.Models.PollOption
import kotlinx.coroutines.flow.Flow

class OfflinePollOptionRepository(
    private val pollOptionDao: PollOptionDao
): PollOptionRepository
{
    override fun getAllPollOptionStream(): Flow<List<PollOption>> = pollOptionDao.getAllPollOptions()

    override fun getPollOptionStream(id: Int): Flow<PollOption?> = pollOptionDao.getPollOption(id)

    override suspend fun insertPollOption(pollOption: PollOption) = pollOptionDao.insert(pollOption)

    override suspend fun deletePollOption(pollOption: PollOption) = pollOptionDao.delete(pollOption)

    override suspend fun updatePollOption(pollOption: PollOption) = pollOptionDao.update(pollOption)

}