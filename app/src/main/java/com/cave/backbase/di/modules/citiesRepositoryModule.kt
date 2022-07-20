package com.cave.backbase.di.modules

import android.content.res.AssetManager
import com.cave.backbase.data.local.CitiesLocal
import com.cave.backbase.data.local.CitiesLocalImp
import com.cave.backbase.data.repositories.CitiesRepository
import com.cave.backbase.data.repositories.CitiesRepositoryImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val citiesRepositoryModule = module {
    single<CitiesLocal> { CitiesLocalImp(get()) }
    single<CitiesRepository> { CitiesRepositoryImp(citiesLocal = get(), coroutineContextProvider = get()) }
}
