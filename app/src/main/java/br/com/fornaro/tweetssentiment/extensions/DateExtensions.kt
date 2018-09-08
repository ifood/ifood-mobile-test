package br.com.fornaro.tweetssentiment.extensions

import android.text.format.DateUtils
import java.util.*

fun Date.getRelativeOneDayTimeSpanString(): CharSequence {
    return DateUtils.getRelativeTimeSpanString(time, Date().time, DateUtils.DAY_IN_MILLIS)
}