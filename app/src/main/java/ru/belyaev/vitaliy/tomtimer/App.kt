package ru.belyaev.vitaliy.tomtimer

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.belyaev.vitaliy.tomtimer.di.appModule
import ru.belyaev.vitaliy.tomtimer.di.viewModelModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()

        appContext = applicationContext

        if (!isReleaseBuild) Timber.plant(Timber.DebugTree())

        AndroidThreeTen.init(this)

        setDefaultThemeSettings()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        // Code called before ContentProvider.onCreate method
        setupKoinDi()
    }

    private fun setDefaultThemeSettings() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    private fun setupKoinDi() {
        startKoin {
            androidContext(this@App)
            modules(appModule, viewModelModule)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set

        val isReleaseBuild: Boolean
            get() = BuildConfig.BUILD_TYPE == RELEASE_BUILD_TYPE

        private const val RELEASE_BUILD_TYPE = "release"

        fun isDarkThemeEnabled(context: Context): Boolean {
            return when (context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> true
                else -> false
            }
        }
    }
}