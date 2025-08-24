package com.example.gtfh.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gtfh.data.local.entity.TimeLog
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val timeLogs by viewModel.timeLogs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Time Logs",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(timeLogs) { log ->
                TimeLogItem(log)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.startSession() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Start Office Session")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.endSession() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("End Office Session")
        }
    }
}

@Composable
fun TimeLogItem(log: TimeLog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${log.date}")
            Text(text = "Entry Time: ${formatTimestamp(log.entryTime)}")
            Text(text = "Exit Time: ${log.exitTime?.let { formatTimestamp(it) } ?: "In Progress"}")
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
