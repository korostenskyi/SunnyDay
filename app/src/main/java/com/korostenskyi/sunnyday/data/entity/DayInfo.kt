package com.korostenskyi.sunnyday.data.entity

import com.google.gson.annotations.SerializedName

data class DayInfo (
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("solar_noon") val solarNoon: String,
    @SerializedName("day_length") val dayLength: String
)