package com.cave.backbase.data.local

import com.cave.backbase.data.model.City

class CitiesLocalImp : CitiesLocal {
    override suspend fun getCitiesList(): List<City> {
        return listOf<City>()
    }
}
