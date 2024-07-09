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
    fun getAll(): Flow<List<TenantChat>>

    @Query("SELECT * FROM tenant_chat WHERE chatId = :chatId")
    fun getByChatId(chatId: Int): Flow<List<TenantChat>>

    @Query("SELECT * FROM tenant_chat WHERE tenantId = :tenantId")
    fun getByTenantId(tenantId: Int): Flow<List<TenantChat>>

    @Query("SELECT tenantId FROM tenant_chat WHERE (chatId in (SELECT chatId FROM tenant_chat WHERE tenantId = :tenantId)) ")
    fun getTenantsInSameChatS(tenantId: Int): List<Int>
}