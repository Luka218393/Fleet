package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fleet.domain.Models.PollOption
import kotlinx.coroutines.flow.Flow

@Dao
interface PollOptionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pollOption: PollOption)

    @Update
    suspend fun update(pollOption: PollOption)

    @Delete
    suspend fun delete(pollOption: PollOption)

    @Query("SELECT * from poll_options WHERE id = :id")
    fun getPollOption(id: Int): Flow<PollOption>

    @Query("SELECT * from poll_options ORDER BY value ASC")
    fun getAllPollOptions(): Flow<List<PollOption>>
}