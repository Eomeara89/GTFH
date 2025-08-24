package com.example.gtfh.data.repository

import com.example.gtfh.data.local.dao.TimeLogDao
import com.example.gtfh.data.local.entity.TimeLog
import kotlinx.coroutines.flow.Flow

class TimeLogRepository(private val timeLogDao: TimeLogDao) {

    fun getAllLogs(): Flow<List<TimeLog>> = timeLogDao.getAllLogs()

    suspend fun startOfficeSession() {
        val log = TimeLog(
            entryTime = System.currentTimeMillis(),
            date = java.time.LocalDate.now().toString()
        )
        timeLogDao.insertLog(log)
    }

    suspend fun endOfficeSession() {
        val currentSession = timeLogDao.getCurrentOfficeSession()
        currentSession?.let {
            it.exitTime = System.currentTimeMillis()
            timeLogDao.updateLog(it)
        }
    }
}
