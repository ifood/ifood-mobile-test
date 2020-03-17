package com.ifood.challenge.base.extensions

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val TWITTER_DATE_FORMAT = "E MMM dd HH:mm:ss Z yyyy"
const val US_DATE_FORMAT = "HH:mm MM/dd/yyyy"

fun String.Companion.empty() = ""

fun String?.fromTwitterDateToUsDate(): String {
    if (this == null) return ""
    return try {
        val twitterDate = SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.getDefault()).parse(this)
        val usDate = SimpleDateFormat(US_DATE_FORMAT, Locale.getDefault()).format(twitterDate)
        usDate
    } catch (error: ParseException) {
        Timber.e(error)
        this
    }
}
