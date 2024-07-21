package com.example.fleet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fleet.data.daos.ApartmentDao
import com.example.fleet.data.daos.BuildingDao
import com.example.fleet.data.daos.ChatDao
import com.example.fleet.data.daos.MessageDao
import com.example.fleet.data.daos.NotificationDao
import com.example.fleet.data.daos.PollDao
import com.example.fleet.data.daos.PollOptionDao
import com.example.fleet.data.daos.SettingsDao
import com.example.fleet.data.daos.SubTaskDao
import com.example.fleet.data.daos.TaskDao
import com.example.fleet.data.daos.TenantChatDao
import com.example.fleet.data.daos.TenantDao
import com.example.fleet.domain.Models.Apartment
import com.example.fleet.domain.Models.Building
import com.example.fleet.domain.Models.Chat
import com.example.fleet.domain.Models.Message
import com.example.fleet.domain.Models.Notification
import com.example.fleet.domain.Models.Poll
import com.example.fleet.domain.Models.PollOption
import com.example.fleet.domain.Models.Settings
import com.example.fleet.domain.Models.SubTask
import com.example.fleet.domain.Models.Task
import com.example.fleet.domain.Models.Tenant
import com.example.fleet.domain.Models.TenantChat

@Database(entities = [
    Apartment::class,
    Building::class,
    Chat::class,
    Message::class,
    Notification::class,
    Poll::class,
    PollOption::class,
    Settings::class,
    Task::class,
    TenantChat::class,
    Tenant::class,
    SubTask::class],
    version = 23,
    exportSchema = false,
)
@TypeConverters(TypeConverte::class)
abstract class FleetDatabase : RoomDatabase() {

    abstract fun apartmentDao(): ApartmentDao
    abstract fun buildingDao(): BuildingDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun notificationDao(): NotificationDao
    abstract fun pollDao(): PollDao
    abstract fun pollOptionDao(): PollOptionDao
    abstract fun settingsDao(): SettingsDao
    abstract fun taskDao(): TaskDao
    abstract fun tenantChatDao(): TenantChatDao
    abstract fun tenantDao(): TenantDao
    abstract fun subTaskDao(): SubTaskDao

    companion object {
        @Volatile
        private var Instance: FleetDatabase? = null

        fun getDatabase(context: Context): FleetDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FleetDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}