package ru.belyaev.vitaliy.tomtimer

import android.app.Application
import dev.zacsweers.ticktock.android.tzdb.AndroidTzdbZoneRules
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AndroidTzdbZoneRules.init(this)
    }
}