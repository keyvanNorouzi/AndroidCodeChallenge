package com.cave.backbase.app

import android.app.Application
import com.cave.backbase.di.component.applicationComponent
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            // Logger Object
            androidLogger()
            androidContext(this@App)
            if (BuildConfig.DEBUG) {
                AndroidLogger(Level.DEBUG)
            } else {
                AndroidLogger(Level.NONE)
            }
            // modules
            modules(applicationComponent)
        }
    }
}
