package com.test.nytimessample

import android.app.Application
import com.test.nytimessample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
//            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(listOf(appModule))
        }
    }
}