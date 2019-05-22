package com.korostenskyi.sunnyday.di

import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSource
import com.korostenskyi.sunnyday.data.network.SunriseSunsetDataSourceImpl
import com.korostenskyi.sunnyday.data.network.api.ApiService
import com.korostenskyi.sunnyday.data.repository.Repository
import com.korostenskyi.sunnyday.data.repository.RepositoryImpl
import com.korostenskyi.sunnyday.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { ApiService() }
    single<SunriseSunsetDataSource> { SunriseSunsetDataSourceImpl(get()) }
    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}