package com.pe.mascotapp.vistas.extension

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

/**
 * Converts a Long millis value to a readable date format.
 *
 * The output format will be:
 * `Jueves, 24 de marzo de 2023`.
 */
fun Long.toDateFormat(): String {
    val parsed: Instant = Instant.fromEpochMilliseconds(this)
    val dateFormat: DateTimeFormat<LocalDate> = LocalDate.Format {
        dayOfWeek(DAY_OF_WEEK_NAMES)
        char(',')
        char(Typography.nbsp)
        dayOfMonth()
        char(Typography.nbsp)
        chars("de")
        char(Typography.nbsp)
        monthName(MONTHS_LOWERCASE_SPANISH_FULL)
        char(Typography.nbsp)
        chars("de")
        year()
    }
    val localDateTime: LocalDateTime = parsed.toLocalDateTime(TimeZone.currentSystemDefault())

    val localDateFormated: String = localDateTime.date.format(dateFormat)

    return localDateFormated
}

/**
 * Converts a Long millis value to a readable date format.
 *
 * The output format will be:
 * `Jueves 24 marzo, 12:00`.
 */
fun Long.toShortDateWithTime(): String {
    val parsed: Instant = Instant.fromEpochMilliseconds(this)
    val dateFormat: DateTimeFormat<LocalDate> = LocalDate.Format {
        dayOfWeek(DAY_OF_WEEK_NAMES)
        char(Typography.nbsp)
        dayOfMonth()
        char(Typography.nbsp)
        monthName(MONTHS_LOWERCASE_SPANISH_FULL)
    }
    val timeFormat = LocalTime.Format {
        hour()
        char(':')
        minute()
    }

    val localDateTime: LocalDateTime = parsed.toLocalDateTime(TimeZone.UTC)
    val localDateFormated: String = localDateTime.date.format(dateFormat)
    val localTimeFormated: String = localDateTime.time.format(timeFormat)

    return "$localDateFormated, $localTimeFormated"
}