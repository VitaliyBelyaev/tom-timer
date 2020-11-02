package ru.belyaev.vitaliy.tomtimer

import java.time.LocalDateTime

interface CurrentDateTimeProvider {

    fun localDateTime(): LocalDateTime
}