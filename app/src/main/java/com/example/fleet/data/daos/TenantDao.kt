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
    fun getAll(): Flow<List<Tenant>>

    @Query("SELECT * from tenants WHERE id = :id")
    fun getById(id: Int): Flow<Tenant?>

    @Query("SELECT Name from tenants where id = :id ")
    fun getNameById(id:Int): String

    @Query("SELECT * FROM tenants WHERE apartmentId IN ( SELECT id FROM apartments WHERE buildingId = :buildingId) ")
    fun getTenantsByBuildingId(buildingId:Int): List<Tenant>

    @Query("SELECT id, name, surname  FROM tenants")
    fun getTenantsIdAndName(): Flow<List<TenantIdAndName>>
}

data class TenantIdAndName(
    val id: Int,
    val name: String,
    val surname: String
)