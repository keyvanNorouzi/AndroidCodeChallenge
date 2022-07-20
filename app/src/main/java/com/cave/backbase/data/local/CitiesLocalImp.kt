package com.cave.backbase.data.local

import android.content.res.AssetManager
import android.content.res.AssetManager.ACCESS_STREAMING
import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.IOException

class CitiesLocalImp(private val assetManager: AssetManager) : CitiesLocal {

    companion object {
        const val CITIES_FILE_NAME = "cities.txt"
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Throws(IOException::class)
    override suspend fun getCitiesList(): Result<List<City>> {
        return try {
            val list: List<City>
            assetManager.open(CITIES_FILE_NAME, ACCESS_STREAMING).use { inputStream ->
                list = (Json.decodeFromStream(inputStream))
            }
            Result.Success(list.sortedWith(compareBy({ it.city }, { it.country })))
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}
