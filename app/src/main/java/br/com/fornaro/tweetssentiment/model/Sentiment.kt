package br.com.fornaro.tweetssentiment.model

import android.content.Context
import android.support.v4.content.ContextCompat
import br.com.fornaro.tweetssentiment.R

enum class Sentiment {
    Happy, Neutral, Sad, None;

    fun getSentimentText(context: Context): String {
        return when (this) {
            Sentiment.Happy -> context.getString(R.string.happy, "\ud83d\ude03")
            Sentiment.Neutral -> context.getString(R.string.neutral, "\ud83d\ude10")
            Sentiment.Sad -> context.getString(R.string.sad, "\ud83d\ude14")
            else -> ""
        }
    }

    fun getSentimentColor(context: Context): Int {
        return when (this) {
            Sentiment.Happy -> ContextCompat.getColor(context, R.color.happy)
            Sentiment.Neutral -> ContextCompat.getColor(context, R.color.neutral)
            Sentiment.Sad -> ContextCompat.getColor(context, R.color.sad)
            else -> 0
        }
    }
}