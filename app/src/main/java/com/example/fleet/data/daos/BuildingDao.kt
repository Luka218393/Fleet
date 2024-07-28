package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Building
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {

    @Upsert
    suspend fun upsert(building: Building)

    @Delete
    suspend fun delete(building: Building)

    @Query("SELECT * FROM buildings")
    fun getAll(): Flow<List<Building>>

    @Query("SELECT * from buildings WHERE id = :id")
    fun getById(id: String): Flow<Building>

    @Query("SELECT EXISTS (SELECT 1 FROM buildings WHERE id = :id)")
    fun exists(id: String): Boolean
}