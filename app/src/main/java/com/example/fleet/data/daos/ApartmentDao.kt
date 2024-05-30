package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Apartment
import com.example.fleet.domain.Models.PollOption
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
    fun getById(id: Int): Flow<Apartment>
}
/*
@Dao
interface Dao {

    @Upsert
    suspend fun upsert()

    @Delete
    suspend fun delete()

    @Query("SELECT * FROM ")
    suspend fun getAll(): Flow<List<>>

    @Query("SELECT * from  WHERE id = :id")
    fun getById(id: Int): Flow<>
}*/