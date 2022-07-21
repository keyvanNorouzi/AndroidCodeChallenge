package com.cave.backbase.data.repositories

import com.cave.backbase.data.local.CitiesLocal
import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import com.cave.backbase.utils.CoroutineContextProvider
import com.cave.backbase.utils.extentions.prefixSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CitiesRepositoryImp(
    private val citiesLocal: CitiesLocal,
    private val coroutineContextProvider: CoroutineContextProvider
) : CitiesRepository {
    private var cities: List<City>? = null

    private fun fetchCities(): Flow<Result<List<City>>> = flow {
        emit(Result.Loading)
        emit(citiesLocal.getCitiesList())
    }.flowOn(coroutineContextProvider.io)

    override fun getNextCities(lastItemIndex: Int, numberOfRows: Int): Flow<Result<List<City>>> =
        flow {
            if (cities == null) {
                emit(Result.Loading)
                when (val result = citiesLocal.getCitiesList()) {
                    is Result.Loading -> {
                        emit(Result.Loading)
                    }
                    is Result.Success -> {
                        cities = result.data
                        emit(
                            Result.Success(
                                getCitiesFormList(
                                    lastItemIndex,
                                    lastItemIndex + numberOfRows
                                )
                            )
                        )
                    }
                    is Result.Error -> {
                        emit(Result.Error(result.exception))
                    }
                }
            } else {
                emit(Result.Success(getCitiesFormList(lastItemIndex, lastItemIndex + numberOfRows)))
            }
        }.flowOn(coroutineContextProvider.io)

    override fun searchCityByPrefix(prefix: String): Flow<Result<List<City>>?> = flow {
        emit(Result.Loading)
        emit(
            Result.Success(
                cities!!.prefixSearch<City>(
                    fromIndex = 0,
                    toIndex = cities!!.size,
                    prefix = prefix
                )
            )
        )
    }.flowOn(coroutineContextProvider.io)

    private fun getCitiesFormList(fromIndex: Int, toIndex: Int): List<City>? {
        return if (toIndex > (cities?.size ?: 0)) {
            return cities?.subList(fromIndex, ((cities?.size ?: 0) - 1))
        } else {
            cities?.subList(fromIndex, toIndex)
        }
    }
}
