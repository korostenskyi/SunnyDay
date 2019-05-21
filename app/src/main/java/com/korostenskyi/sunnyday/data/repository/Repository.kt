package com.korostenskyi.sunnyday.data.repository

import androidx.lifecycle.MutableLiveData
import com.korostenskyi.sunnyday.data.entity.DayInfo
import com.korostenskyi.sunnyday.data.network.api.util.DataWrapper

interface Repository {

    val fetchedDayInfo: MutableLiveData<DataWrapper<DayInfo>>

    suspend fun fetchDayInfoByCoordinated(latitude: Double, longitude: Double)
}