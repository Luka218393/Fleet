package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Poll
import kotlinx.coroutines.flow.Flow

@Dao
interface PollDao {

    @Upsert
    suspend fun upsert(poll: Poll)

    @Delete
    suspend fun delete(poll: Poll)

    @Query("SELECT * FROM polls")
    fun getAll(): Flow<List<Poll>>

    @Query("SELECT * from polls WHERE id = :id")
    fun getById(id: Int): Flow<Poll>

    @Query("SELECT * from polls WHERE buildingid = :id")
    fun getByBuildingId(id: Int): Flow<List<Poll>>
}