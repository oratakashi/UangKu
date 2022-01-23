package com.oratakashi.uangku

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.oratakashi.uangku.di.dbModule
import com.oratakashi.uangku.di.libModule
import com.oratakashi.uangku.di.reqresModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                dbModule,
                libModule,
                reqresModule
            ))
        }
    }
}