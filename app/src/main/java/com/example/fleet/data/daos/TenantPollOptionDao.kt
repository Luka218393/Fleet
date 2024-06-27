package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.TenantPollOption
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantPollOptionDao {

    @Upsert
    suspend fun upsert(tenantPollOption: TenantPollOption)

    @Delete
    suspend fun delete(tenantPollOption: TenantPollOption)

    @Query("SELECT * FROM tenant_pollOption")
    fun getAll(): Flow<List<TenantPollOption>>

    @Query("SELECT * FROM tenant_pollOption WHERE pollOptionId = :pollOptionId")
    fun getByPollOptionId(pollOptionId: Int): Flow<List<TenantPollOption>>

    @Query("SELECT * FROM tenant_pollOption WHERE tenantId = :tenantId")
    fun getByTenantId(tenantId: Int): Flow<List<TenantPollOption>>
}