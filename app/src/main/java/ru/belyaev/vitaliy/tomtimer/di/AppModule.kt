package ru.belyaev.vitaliy.tomtimer.di

import android.content.Context
import ru.belyaev.vitaliy.tomtimer.CurrentDateTimeProvider
import ru.belyaev.vitaliy.tomtimer.CurrentDateTimeProviderImpl
import timber.log.Timber
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        Timber.tag("RTRT").d("init of AppModule")
        bind(CurrentDateTimeProvider::class.java)
            .toInstance(CurrentDateTimeProviderImpl())
    }
}