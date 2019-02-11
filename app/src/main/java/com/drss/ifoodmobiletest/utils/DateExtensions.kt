package com.drss.ifoodmobiletest.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.formatTweetDate(): String {
    val simpleDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val myDate = simpleDateFormat.parse(this)

    val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formater.format(myDate)
}