package com.korostenskyi.sunnyday.data.network.api.util

data class DataWrapper<out T> (
    val data: T? = null,
    val error: Failure? = null
)