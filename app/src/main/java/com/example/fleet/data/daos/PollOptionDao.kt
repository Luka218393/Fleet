package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.fleet.domain.Models.PollOption
import kotlinx.coroutines.flow.Flow

@Dao
interface PollOptionDao {
    @Upsert
    suspend fun insert(pollOption: PollOption)

    @Delete
    suspend fun delete(pollOption: PollOption)

    @Query("SELECT * from poll_options WHERE id = :id")
    fun getPollOption(id: Int): Flow<PollOption>

    @Query("SELECT * from poll_options ORDER BY value ASC")
    fun getAllPollOptions(): Flow<List<PollOption>>
}