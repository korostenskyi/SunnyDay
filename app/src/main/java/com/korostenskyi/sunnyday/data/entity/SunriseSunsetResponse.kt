package com.korostenskyi.sunnyday.data.entity

import com.google.gson.annotations.SerializedName

data class SunriseSunsetResponse (
    @SerializedName("results") val data: DayInfo,
    @SerializedName("status") val status: String
)