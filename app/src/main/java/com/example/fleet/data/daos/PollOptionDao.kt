package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.PollOption
import kotlinx.coroutines.flow.Flow

@Dao
interface PollOptionDao {
    @Upsert
    suspend fun upsert(pollOption: PollOption)

    @Delete
    suspend fun delete(pollOption: PollOption)

    @Query("SELECT * from poll_options WHERE id = :id")
    fun getById(id: String): Flow<PollOption>

    @Query("SELECT * from poll_options ORDER BY value ASC")
    fun getAll(): Flow<List<PollOption>>

    @Query("SELECT * from poll_options WHERE pollid = :id")
    fun getByPollId(id: String): Flow<List<PollOption>>

    @Query("""
        SELECT * FROM poll_options WHERE pollId IN(
            SELECT polls.id FROM polls WHERE polls.buildingId == :buildingId
        )
    """)
    fun getByBuildingId(buildingId: String): Flow<List<PollOption>>
}