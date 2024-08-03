package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Tenant
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
    fun getByChatId(chatId: String): Flow<List<TenantChat>>

    @Query("SELECT * from chats WHERE id in (SELECT chatId FROM tenant_chat WHERE tenantId = :tenantId)")
    fun getChatsOfATenant(tenantId: String): Flow<List<Chat>>

    //Todo remove tenant that has original Id,
    // query is not good
    @Query("""
    SELECT t.* 
    FROM tenants t
    JOIN apartments a ON t.apartmentId = a.id
    JOIN (
        SELECT a.buildingId 
        FROM apartments a
        JOIN tenants t ON a.id = t.apartmentId
        WHERE t.id = :tenantId
        LIMIT 1
    ) ta ON a.buildingId = ta.buildingId
    WHERE t.id NOT IN (
        SELECT tc.tenantId 
        FROM tenant_chat tc
        JOIN chats c ON tc.chatId = c.id
        WHERE c.isPrivate = 1
        AND tc.chatId IN (
            SELECT tc.chatId 
            FROM tenant_chat tc
            WHERE tc.tenantId = :tenantId
        )
    )           
    """)
    fun getTenantsForNewPersonalChat(tenantId: String): List<Tenant>

    @Query(
        """
            select tenantId from tenant_chat where chatId == :chatId
        """
    )
    fun getTenantIdsFromChat(chatId: String): List<String>
}







