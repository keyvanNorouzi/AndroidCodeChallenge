package com.cave.backbase.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val country: String,
    @SerialName("name") val city: String,
    val _id: Long,
    val coord: CityLocation
)

@Serializable
data class CityLocation(val lat: Double, val lon: Double)
