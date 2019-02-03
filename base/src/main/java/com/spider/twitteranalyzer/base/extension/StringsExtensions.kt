package com.spider.twitteranalyzer.base.extension

import android.util.Base64
import java.text.SimpleDateFormat
import java.util.*


fun String.encode(): String = Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
fun String.reformatDate(
    from: String = "EEE MMM dd hh:mm:ss z yyyy",
    to: String = "MM/dd/yyyy hh:mm:ss aa"
): String =
    this.let { dateText ->
        SimpleDateFormat(from, Locale.ENGLISH)
            .parse(dateText)
            .let {
                SimpleDateFormat(to, Locale.ENGLISH).format(it)
            }
    }

