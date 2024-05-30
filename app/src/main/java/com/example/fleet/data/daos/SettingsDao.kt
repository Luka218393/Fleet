package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Upsert
    suspend fun upsert(settings: Settings)

    @Delete
    suspend fun delete(settings: Settings)

    @Query("SELECT * FROM settings")
    fun getAll(): Flow<List<Settings>>

    @Query("SELECT * from settings WHERE id = :id")
    fun getById(id: Int): Flow<Settings>
}