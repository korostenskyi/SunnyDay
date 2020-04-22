package com.korostenskyi.sunnyday.di

import com.korostenskyi.sunnyday.BuildConfig
import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSource
import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSourceImpl
import com.korostenskyi.sunnyday.data.network.api.ApiService
import com.korostenskyi.sunnyday.data.repository.Repository
import com.korostenskyi.sunnyday.data.repository.RepositoryImpl
import com.korostenskyi.sunnyday.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinModule = module {

    single(named("baseUrl")) { BuildConfig.SUNRISE_SUNSET_BASE_URL }

    single { ApiService(get(named("baseUrl"))) }

    single<SunriseSunsetDataSource> { SunriseSunsetDataSourceImpl(get()) }

    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}