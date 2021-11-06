package com.oratakashi.uangku.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val libModule = module {
    single { CompositeDisposable() }
}