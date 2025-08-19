package com.example.gtfh.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_logs")
data class TimeLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val entryTime: Long,
    var exitTime: Long? = null,
    val date: String // e.g., "2024-08-20"
)
