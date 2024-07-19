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
    fun getByChatId(chatId: Int): Flow<List<TenantChat>>

    @Query("SELECT * from chats WHERE id in (SELECT chatId FROM tenant_chat WHERE tenantId = :tenantId)")
    fun getChatsOfATenant(tenantId: Int): Flow<List<Chat>>

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
    fun getTenantsForNewPrivateChats(tenantId: Int): List<Tenant>
}


/*
SELECT * FROM tenants WHERE --Select tenants to display
tenants.id NOT IN
(SELECT tenantId FROM tenant_chat WHERE chatId IN--Finds tenants that are not in private chat with original tenant
(SELECT chats.id from chats WHERE  isPrivate == 1 AND chats.id IN --Filters those chats only to those that are private
(SELECT chatId FROM tenant_chat WHERE tenantId = :tenantId)))--Selects all chats that the tenant is in
AND tenants.apartmentId IN (--Selects all tenants that live in those apartments
SELECT apartments.id FROM apartments WHERE apartments.buildingId IN --Selects all apartments from taht building
(SELECT buildingId FROM apartments WHERE apartments.id IN
(SELECT apartmentId FROM tenants WHERE tenants.id == :tenantId LIMIT 1) LIMIT 1))--Selects Apartment and building that the tenant lives in




*/






