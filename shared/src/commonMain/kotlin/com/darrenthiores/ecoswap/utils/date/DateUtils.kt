package com.darrenthiores.ecoswap.utils.date

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtils {
    fun formatDate(
        timestamp: Long
    ): String {
        val date = Instant
            .fromEpochMilliseconds(timestamp)
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            )

        val month = if (date.monthNumber < 10) "0${date.monthNumber}" else date.monthNumber
        val day = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth
        val year = date.year
        val hour = if (date.hour < 10) "0${date.hour}" else date.hour
        val minute = if (date.minute < 10) "0${date.minute}" else date.minute

        return "$year-$month-${day}, $hour:$minute"
    }
}