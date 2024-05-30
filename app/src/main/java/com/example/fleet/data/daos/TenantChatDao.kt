package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.TenantChat
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantChatDao {

    @Upsert
    suspend fun upsert(tenantChat: TenantChat)

    @Delete
    suspend fun delete(tenantChat: TenantChat)

    @Query("SELECT * FROM tenant_chat")
    suspend fun getAll(): Flow<List<TenantChat>>

}