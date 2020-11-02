package ru.belyaev.vitaliy.tomtimer

import java.time.LocalDateTime
import java.time.ZoneId
import timber.log.Timber

class CurrentDateTimeProviderImpl : CurrentDateTimeProvider {

    private val zoneId = ZoneId.systemDefault()


    init {
        Timber.tag("RTRT").d("init of CurrentDateTimeProviderImpl")
    }
    override fun localDateTime(): LocalDateTime {
        return LocalDateTime.now(zoneId)
    }
}