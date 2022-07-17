package com.cave.backbase.app

import android.app.Application
import com.cave.backbase.di.component.applicationComponent
import org.koin.android.BuildConfig
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        GlobalContext.startKoin {
            // Logger Object
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
