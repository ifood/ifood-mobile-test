package com.study.vipoliveira.tweetanalyst.ui.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.formatTimeStamp(): String{
    val date = SimpleDateFormat(RESPONSE_DATE_PATTERN, Locale.ENGLISH).parse(this)
    return SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH).format(date)
}


