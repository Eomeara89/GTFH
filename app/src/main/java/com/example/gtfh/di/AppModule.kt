package com.example.gtfh.di

import androidx.room.Room
import com.example.gtfh.data.local.database.AppDatabase
import com.example.gtfh.data.repository.TimeLogRepository
import com.example.gtfh.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "gtfh_db"
        ).build()
    }
    single { get<AppDatabase>().timeLogDao() }
    single { TimeLogRepository(get()) }
    viewModel { HomeViewModel(get()) }
}
