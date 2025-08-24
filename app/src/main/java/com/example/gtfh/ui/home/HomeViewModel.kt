package com.example.gtfh.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gtfh.data.local.entity.TimeLog
import com.example.gtfh.data.repository.TimeLogRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TimeLogRepository
) : ViewModel() {

    val timeLogs: StateFlow<List<TimeLog>> = repository.getAllLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun startSession() {
        viewModelScope.launch {
            repository.startOfficeSession()
        }
    }

    fun endSession() {
        viewModelScope.launch {
            repository.endOfficeSession()
        }
    }
}
