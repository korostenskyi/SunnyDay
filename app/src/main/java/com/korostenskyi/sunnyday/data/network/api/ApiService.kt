package com.korostenskyi.sunnyday.data.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService(baseUrl: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val client: ApiClient

    init {
        client = retrofit.create(ApiClient::class.java)
    }

    suspend fun fetchDayInfoByCoordinates(latitude: Double, longitude: Double) = client.fetchDayInfoByCoordinates(latitude, longitude)
}