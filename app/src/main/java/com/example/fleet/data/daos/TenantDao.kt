package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {

    @Upsert
    suspend fun upsert(tenant: Tenant)

    @Delete
    suspend fun delete(tenant: Tenant)

    @Query("SELECT * FROM tenants")
    suspend fun getAll(): Flow<List<Tenant>>

    @Query("SELECT * from tenants WHERE id = :id")
    fun getById(id: Int): Flow<Tenant>
}