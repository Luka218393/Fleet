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
    suspend fun upsertApartment(apartment: Apartment)

    @Delete
    suspend fun deleteApartment(apartment: Apartment)

    @Query("SELECT * FROM apartments")
    suspend fun getAllApartments(): Flow<List<Apartment>>

    /*TODO Stao sam na 13:40*/
}