package com.korostenskyi.sunnyday.di

import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSourceImpl
import com.korostenskyi.sunnyday.data.network.api.ApiService
import com.korostenskyi.sunnyday.data.repository.RepositoryImpl
import org.koin.dsl.module

val koinModule = module {

    single { ApiService() }
    single { SunriseSunsetDataSourceImpl(get()) }
    single { RepositoryImpl(get()) }
}