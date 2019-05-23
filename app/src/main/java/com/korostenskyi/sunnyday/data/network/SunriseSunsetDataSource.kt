package com.korostenskyi.sunnyday.data.network

import androidx.lifecycle.LiveData
import com.korostenskyi.sunnyday.data.entity.DayInfo
import com.korostenskyi.sunnyday.data.network.api.util.DataWrapper

interface SunriseSunsetDataSource {

    val fetchedDayInfo: LiveData<DataWrapper<DayInfo>>

    suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double)
}