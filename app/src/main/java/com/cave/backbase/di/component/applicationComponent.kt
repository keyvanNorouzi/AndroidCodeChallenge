package com.cave.backbase.di.component

import com.cave.backbase.di.modules.applicationModule
import com.cave.backbase.di.modules.citiesRepositoryModule
import com.cave.backbase.di.modules.providerModule
import com.cave.backbase.di.modules.viewModelModule
import org.koin.core.module.Module

val applicationComponent: List<Module> = listOf(
    applicationModule,
    providerModule,
    viewModelModule,
    citiesRepositoryModule
)
