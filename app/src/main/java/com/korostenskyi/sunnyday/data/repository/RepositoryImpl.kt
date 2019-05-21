package com.korostenskyi.sunnyday.data.repository

import androidx.lifecycle.MutableLiveData
import com.korostenskyi.sunnyday.data.entity.DayInfo
import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSource
import com.korostenskyi.sunnyday.data.network.api.util.DataWrapper

class RepositoryImpl(private val dataSource: SunriseSunsetDataSource): Repository {

    override val fetchedDayInfo = MutableLiveData<DataWrapper<DayInfo>>()

    init {
        dataSource.fetchedDayInfo.observeForever { serverResponse ->
            fetchedDayInfo.value = serverResponse
        }
    }

    override suspend fun fetchDayInfoByCoordinated(latitude: Double, longitude: Double) {
        dataSource.fetchDayInfoByCoordinates(latitude, longitude)
    }
}