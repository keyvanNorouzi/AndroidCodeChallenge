package com.cave.backbase.di.modules

import android.content.Context
import com.cave.backbase.app.App
import org.koin.dsl.module

val applicationModule = module {
    //    Provide Application class instance
    single { App.instance }

    // Provide context
    single { get<App>().applicationContext }

    // Provide resources
    single {
        val context = get<Context>()
        context.resources
    }
}
