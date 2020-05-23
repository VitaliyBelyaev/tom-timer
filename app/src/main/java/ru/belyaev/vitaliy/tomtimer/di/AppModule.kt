package ru.belyaev.vitaliy.tomtimer.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.belyaev.vitaliy.tomtimer.BuildConfig

@Suppress("USELESS_CAST")
val appModule = module {
    single {
        androidContext().getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )
    }
    single { FirebaseAnalytics.getInstance(androidContext()) }
}