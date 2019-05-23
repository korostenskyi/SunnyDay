package com.korostenskyi.sunnyday.data.network.api

import com.korostenskyi.sunnyday.data.entity.SunriseSunsetResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("json")
    fun fetchDayInfoByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double
    ): Deferred<SunriseSunsetResponse>
}