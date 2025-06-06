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
    fun getById(id: String): Flow<List<Message>>

    @Query("SELECT * from messages WHERE chatId = :chatId ORDER BY sendingTime DESC")
    fun getByChatId(chatId: String): Flow<List<Message>>

    @Query("SELECT text from messages WHERE chatId = :chatId ORDER BY sendingTime DESC LIMIT 1")
    fun getLastMessageFromChat(chatId: String): String?

    @Query("select text from messages where id = :messageId")
    fun getMessageText(messageId: String): String
}