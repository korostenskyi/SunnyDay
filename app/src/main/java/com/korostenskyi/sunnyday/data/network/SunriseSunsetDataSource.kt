package com.korostenskyi.sunnyday.data.network

import com.korostenskyi.sunnyday.data.entity.SunriseSunsetResponse

interface SunriseSunsetDataSource {

    suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double): SunriseSunsetResponse
}