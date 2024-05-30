package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Upsert
    suspend fun upsert(chat: Chat)

    @Delete
    suspend fun delete(chat: Chat)

    @Query("SELECT * FROM chats")
    fun getAll(): Flow<List<Chat>>

    @Query("SELECT * from chats WHERE id = :id")
    fun getById(id: Int): Flow<Chat>
}