package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Upsert
    suspend fun upsert(message: Message)

    @Delete
    suspend fun delete(message: Message)

    @Query("SELECT * FROM messages")
    fun getAll(): Flow<List<Message>>

    @Query("SELECT * from messages WHERE id = :id")
    fun getById(id: Int): Flow<List<Message>>

    @Query("SELECT * from messages WHERE chatId = :chatid ORDER BY sendingTime")
    fun getByChatId(chatid: Int): Flow<List<Message>>

    @Query("SELECT * from messages WHERE chatId = :chatid ORDER BY sendingTime")
    fun getLastMessagefromChat(chatid: Int): Flow<Message>
}