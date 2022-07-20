package com.cave.backbase.di.modules

import androidx.lifecycle.SavedStateHandle
import com.cave.backbase.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (handle: SavedStateHandle) ->
        ListViewModel(handle, citiesRepository = get())
    }
}
