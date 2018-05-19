package br.com.tweetanalyzer.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class DateFormat {
    private fun convertToDate(date: String): Date =
            SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(date)

    fun convertDate(dateString: String): String =
            SimpleDateFormat("MMM dd yyyy", Locale.US).format(convertToDate(dateString))
}