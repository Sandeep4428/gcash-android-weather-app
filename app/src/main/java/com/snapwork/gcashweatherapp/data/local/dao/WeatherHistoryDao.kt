package com.snapwork.gcashweatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snapwork.gcashweatherapp.data.local.entity.WeatherHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherHistoryEntity)

    @Query("""
        SELECT *
        FROM weather_history
        ORDER BY createdAt DESC
    """)
    fun getHistory(): Flow<List<WeatherHistoryEntity>>

    @Query("DELETE FROM weather_history")
    suspend fun clearHistory()
}