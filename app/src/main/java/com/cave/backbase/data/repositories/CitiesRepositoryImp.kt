package com.cave.backbase.data.repositories

import com.cave.backbase.data.local.CitiesLocal
import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import com.cave.backbase.utils.CoroutineContextProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CitiesRepositoryImp(
    private val citiesLocal: CitiesLocal,
    private val coroutineContextProvider: CoroutineContextProvider
) : CitiesRepository {
    override fun getCitiesList(): kotlinx.coroutines.flow.Flow<Result<List<City>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(citiesLocal.getCitiesList()))
    }.flowOn(coroutineContextProvider.io)
}
