package com.oratakashi.uangku.di

import com.oratakashi.uangku.data.db.UangkuDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { UangkuDatabase.getAppDatabase(androidContext()) }

    single { get<UangkuDatabase>().category() }
    single { get<UangkuDatabase>().transaction() }
}