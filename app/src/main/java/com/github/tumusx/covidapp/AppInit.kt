package com.github.tumusx.covidapp

import android.app.Application
import com.github.tumusx.covidapp.country.di.countryModuleKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppInit : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@AppInit)
            modules(listOf(countryModuleKoin))
        }
        super.onCreate()
    }
}