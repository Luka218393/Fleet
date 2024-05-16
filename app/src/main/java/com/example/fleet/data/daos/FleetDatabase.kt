package com.example.fleet.data.daos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fleet.domain.Models.PollOption

@Database(entities = [PollOption::class], version = 2, exportSchema = false)
abstract class FleetDatabase : RoomDatabase() {

    abstract fun pollOptionDao(): PollOptionDao

    companion object {
        @Volatile
        private var Instance: FleetDatabase? = null

        fun getDatabase(context: Context): FleetDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FleetDatabase::class.java, "item_database")
                    .build().also { Instance = it }
            }
        }
    }
}