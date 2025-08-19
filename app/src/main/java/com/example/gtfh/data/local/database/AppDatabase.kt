package com.example.gtfh.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gtfh.data.local.dao.TimeLogDao
import com.example.gtfh.data.local.entity.TimeLog

@Database(entities = [TimeLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timeLogDao(): TimeLogDao
}