package com.example.gtfh

import android.app.Application
import com.example.gtfh.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GtfhApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GtfhApplication)
            modules(appModule)
        }
    }
}

