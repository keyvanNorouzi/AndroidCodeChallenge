package com.cave.backbase.ui.list

import androidx.lifecycle.SavedStateHandle
import com.cave.backbase.base.activity.BaseViewModel
import com.cave.backbase.data.repositories.CitiesRepository

class ListViewModel(
    private val handle: SavedStateHandle,
    private val citiesRepository: CitiesRepository
) : BaseViewModel()
