package com.korostenskyi.sunnyday.data.network

import com.korostenskyi.sunnyday.data.network.api.ApiService

class SunriseSunsetDataSourceImpl(private val apiService: ApiService): SunriseSunsetDataSource {

    override suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double) = apiService.fetchDayInfoByCoordinates(latitude, longitude)
}