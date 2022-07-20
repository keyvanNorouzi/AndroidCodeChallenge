package com.cave.backbase.data.repositories

import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun fetchCities(): Flow<Result<List<City>>>
    fun getNextCities(lastItemIndex: Int, numberOfRows: Int = 10): Flow<Result<List<City>>>
}
