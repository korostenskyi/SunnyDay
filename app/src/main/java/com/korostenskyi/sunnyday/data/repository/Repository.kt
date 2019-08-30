package com.korostenskyi.sunnyday.data.repository

import com.korostenskyi.sunnyday.data.entity.DayInfo

interface Repository {

    suspend fun fetchDayInfoByCoordinated(latitude: Double, longitude: Double): DayInfo
}