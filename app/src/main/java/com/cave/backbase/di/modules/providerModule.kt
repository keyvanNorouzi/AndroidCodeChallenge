package com.cave.backbase.di.modules

import android.content.res.AssetManager
import com.cave.backbase.utils.CoroutineContextProvider
import com.cave.backbase.utils.CoroutineContextProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providerModule = module {
    single<CoroutineContextProvider> {
        CoroutineContextProviderImpl()
    }
    single<AssetManager> { androidContext().assets }
}
