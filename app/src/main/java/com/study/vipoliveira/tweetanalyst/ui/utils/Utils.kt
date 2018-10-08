package com.study.vipoliveira.tweetanalyst.ui.utils

import com.study.vipoliveira.tweetanalyst.domain.model.Sentiment
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {

        fun formatTimeStamp(timestamp: String): String{
            val date = SimpleDateFormat(RESPONSE_DATE_PATTERN, Locale.ENGLISH).parse(timestamp)
            return SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH).format(date)
        }


        fun defineSentiment(sentiment: Sentiment): String {
            return when (sentiment){
                Sentiment.HAPPY -> String(Character.toChars(HAPPY_FACE))
                Sentiment.NEUTRAL -> String(Character.toChars(NEUTRAL_FACE))
                Sentiment.SAD -> String(Character.toChars(SAD_FACE))
            }
        }
    }
}