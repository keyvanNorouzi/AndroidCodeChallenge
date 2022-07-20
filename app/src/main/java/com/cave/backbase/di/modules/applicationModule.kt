package com.cave.backbase.di.modules

import com.cave.backbase.app.App
import org.koin.dsl.module

val applicationModule = module {

    single { App.instance }

    // Provide context
    single { get<App>().applicationContext }
}
