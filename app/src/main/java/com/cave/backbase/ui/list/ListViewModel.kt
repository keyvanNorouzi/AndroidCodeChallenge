package com.cave.backbase.ui.list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cave.backbase.base.activity.BaseViewModel
import com.cave.backbase.data.model.Result
import com.cave.backbase.data.repositories.CitiesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(
    private val handle: SavedStateHandle,
    private val citiesRepository: CitiesRepository
) : BaseViewModel() {

    fun getList() {
        viewModelScope.launch {
            citiesRepository.fetchCities().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        Log.i("getList", "Loading")
                    }
                    is Result.Success -> {
                        Log.i("getList", "Success")
                    }
                    is Result.Error -> {
                        Log.i("getList", "Error ${result.exception}")
                    }
                }
            }
        }
    }
}
