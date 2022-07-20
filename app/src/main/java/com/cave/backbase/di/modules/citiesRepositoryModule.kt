package com.cave.backbase.di.modules

import com.cave.backbase.data.local.CitiesLocal
import com.cave.backbase.data.local.CitiesLocalImp
import com.cave.backbase.data.repositories.CitiesRepository
import com.cave.backbase.data.repositories.CitiesRepositoryImp
import org.koin.dsl.module

val citiesRepositoryModule = module {
    single<CitiesLocal> { CitiesLocalImp() }
    single<CitiesRepository> { CitiesRepositoryImp() }
}
