package com.korostenskyi.sunnyday.data.repository

import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSource

class RepositoryImpl(private val dataSource: SunriseSunsetDataSource): Repository {

    override suspend fun fetchDayInfoByCoordinated(latitude: Double, longitude: Double) = dataSource.fetchDayInfoByCoordinates(latitude, longitude).data
}