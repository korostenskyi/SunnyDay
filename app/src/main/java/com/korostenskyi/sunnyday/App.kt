package com.korostenskyi.sunnyday

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.korostenskyi.sunnyday.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initPlaces()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) printLogger(Level.DEBUG)
            androidContext(this@App)
            modules(koinModule)
        }
    }

    private fun initPlaces() {
        Places.initialize(this, BuildConfig.PLACES_API_KEY)
    }
}