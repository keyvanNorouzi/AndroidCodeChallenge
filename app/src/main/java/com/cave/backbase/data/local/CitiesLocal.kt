package com.cave.backbase.data.local

import com.cave.backbase.data.model.City

interface CitiesLocal {
    suspend fun getCitiesList(): List<City>
}
