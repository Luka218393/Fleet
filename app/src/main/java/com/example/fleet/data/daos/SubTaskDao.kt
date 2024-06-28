package com.example.fleet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fleet.domain.Models.SubTask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTaskDao {

    @Upsert
    suspend fun upsert(subTask: SubTask)

    @Delete
    suspend fun delete(subTask: SubTask)

    @Query("SELECT * FROM SubTasks")
    fun getAll(): Flow<List<SubTask>>

    @Query("SELECT * from SubTasks WHERE id = :id")
    fun getById(id: Int): Flow<SubTask>

    @Query("SELECT * from SubTasks WHERE taskId = :taskId")
    fun getByTaskId(taskId: Int): Flow<List<SubTask>>
}