package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Upsert
    suspend fun upsert(notification: Notification)

    @Delete
    suspend fun delete(notification: Notification)

    @Query("SELECT * FROM notifications")
    suspend fun getAll(): Flow<List<Notification>>

    @Query("SELECT * from notifications WHERE id = :id")
    fun getById(id: Int): Flow<Notification>
}