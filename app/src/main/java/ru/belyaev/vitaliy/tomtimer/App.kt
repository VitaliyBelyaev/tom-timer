package ru.belyaev.vitaliy.tomtimer

import android.app.Application
import dev.zacsweers.ticktock.android.tzdb.AndroidTzdbZoneRules
import ru.belyaev.vitaliy.tomtimer.AppActivity.Companion.APP_SCOPE
import ru.belyaev.vitaliy.tomtimer.di.AppModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.tag("RTRT").d("App onCreate")
        AndroidTzdbZoneRules.init(this)
        initToothpick()
    }

    private fun initToothpick() {
        Toothpick.setConfiguration(Configuration.forProduction())
        Toothpick
            .openScope(APP_SCOPE)
            .installModules(AppModule(this))
    }
}