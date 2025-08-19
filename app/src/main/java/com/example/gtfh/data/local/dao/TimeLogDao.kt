package com.example.gtfh.data.local.dao

import androidx.room.*
import androidx.room.Dao
import com.example.gtfh.data.local.entity.TimeLog
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(timeLog: TimeLog)

    @Update
    suspend fun updateLog(timeLog: TimeLog)

    @Query("SELECT * FROM time_logs WHERE id = :id")
    suspend fun getLogById(id: Int): TimeLog?

    @Query("SELECT * FROM time_logs ORDER BY entryTime DESC")
    fun getAllLogs(): Flow<List<TimeLog>>

    @Query("SELECT * FROM time_logs WHERE exitTime IS NULL ORDER BY entryTime DESC LIMIT 1")
    suspend fun getCurrentOfficeSession(): TimeLog?
}