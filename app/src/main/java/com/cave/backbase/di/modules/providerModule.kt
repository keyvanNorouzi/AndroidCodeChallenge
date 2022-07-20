package com.cave.backbase.di.modules

import com.cave.backbase.utils.CoroutineContextProvider
import com.cave.backbase.utils.CoroutineContextProviderImpl
import org.koin.dsl.module

val providerModule = module {
    single<CoroutineContextProvider> {
        CoroutineContextProviderImpl()
    }
}
