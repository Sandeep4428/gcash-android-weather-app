package com.snapwork.gcashweatherapp.di

import android.content.Context
import androidx.room.Room
import com.snapwork.gcashweatherapp.data.local.dao.UserDao
import com.snapwork.gcashweatherapp.data.local.dao.WeatherHistoryDao
import com.snapwork.gcashweatherapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gcash_weather_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(
        database: AppDatabase
    ): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideWeatherHistoryDao(
        database: AppDatabase
    ): WeatherHistoryDao {

        return database.weatherHistoryDao()
    }
}