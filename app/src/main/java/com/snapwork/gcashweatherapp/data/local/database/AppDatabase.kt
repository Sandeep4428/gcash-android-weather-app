package com.snapwork.gcashweatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snapwork.gcashweatherapp.data.local.entity.UserEntity
import com.snapwork.gcashweatherapp.data.local.dao.UserDao
import com.snapwork.gcashweatherapp.data.local.dao.WeatherHistoryDao
import com.snapwork.gcashweatherapp.data.local.entity.WeatherHistoryEntity

@Database(
    entities = [
        UserEntity::class,
        WeatherHistoryEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun weatherHistoryDao(): WeatherHistoryDao
}