package com.korostenskyi.sunnyday

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.korostenskyi.sunnyday.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Places.initialize(this, resources.getString(R.string.places_api_key))
        startKoin {
            androidContext(this@App)
            modules(koinModule)
        }
    }
}