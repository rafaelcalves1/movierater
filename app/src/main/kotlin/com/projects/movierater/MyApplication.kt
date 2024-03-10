package com.projects.movierater

import android.app.Application
import com.projects.movierater.data.di.repositoryModule
import com.projects.movierater.data.di.serviceModule
import com.projects.movierater.features.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(
                repositoryModule,
                serviceModule,
                viewModelModule
            )
        }
    }
}