package com.cave.backbase.data.model

import java.io.Serializable

sealed class Result<out T : Any> : Serializable {
    data class Success<out T : Any>(val data: T?) : Result<T>()
    object Loading : Result<Nothing>()
}
