package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Apartment
import kotlinx.coroutines.flow.Flow


/*
This is the place where you write all functions to manage Apartments in database
*/
@Dao
interface ApartmentDao {

    @Upsert
    suspend fun upsert(apartment: Apartment)

    @Delete
    suspend fun delete(apartment: Apartment)

    @Query("SELECT * FROM apartments")
    fun getAll(): Flow<List<Apartment>>

    @Query("SELECT * from apartments WHERE id = :id")
    fun getById(id: String): Flow<Apartment>
}
