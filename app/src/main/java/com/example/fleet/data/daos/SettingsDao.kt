package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Settings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Upsert
    suspend fun upsert(settings: Settings)

    @Query("SELECT * from settings")
    fun get(): Flow<Settings>
}