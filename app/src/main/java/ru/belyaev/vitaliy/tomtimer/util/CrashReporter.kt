package ru.belyaev.vitaliy.tomtimer.util

import com.google.firebase.crashlytics.FirebaseCrashlytics
import ru.belyaev.vitaliy.tomtimer.App
import ru.belyaev.vitaliy.tomtimer.BuildConfig
import timber.log.Timber

object CrashReporter {
    @JvmStatic
    fun logException(
        t: Throwable,
        msg: String = "Will be saved to crash manager in non debug mode"
    ) {
        if (BuildConfig.DEBUG) {
            Timber.e(t, msg)
            return
        }

        if (!App.isReleaseBuild) {
            Timber.e(t, msg)
        }

        FirebaseCrashlytics.getInstance().log(msg)
        FirebaseCrashlytics.getInstance().recordException(t)
    }

    @JvmStatic
    fun log(msg: String) {
        if (BuildConfig.DEBUG) {
            Timber.i(msg)
            return
        }

        if (!App.isReleaseBuild) {
            Timber.i(msg)
        }

        FirebaseCrashlytics.getInstance().log(msg)
    }
}
