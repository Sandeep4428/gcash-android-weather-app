package com.snapwork.gcashweatherapp.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.snapwork.gcashweatherapp.data.local.entity.UserEntity
import com.snapwork.gcashweatherapp.data.local.dao.UserDao

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}