package com.cave.backbase.data.repositories

import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getNextCities(lastItemIndex: Int, numberOfRows: Int = 100): Flow<Result<List<City>>>
}
