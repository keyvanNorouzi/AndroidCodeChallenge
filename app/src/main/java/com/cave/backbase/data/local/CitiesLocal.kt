package com.cave.backbase.data.local

import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result

interface CitiesLocal {
    suspend fun getCitiesList(): Result<List<City>>
}
