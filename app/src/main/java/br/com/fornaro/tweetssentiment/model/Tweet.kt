package br.com.fornaro.tweetssentiment.model

import android.content.Context
import android.support.v4.content.ContextCompat
import br.com.fornaro.tweetssentiment.R
import com.google.gson.annotations.SerializedName

data class Tweet(
        val text: String,
        @SerializedName("created_at") val createdAt: String,
        var sentiment: Sentiment? = null
) {
    fun getSentimentText(context: Context): String {
        return when (sentiment) {
            Sentiment.Happy -> context.getString(R.string.happy, "\ud83d\ude03")
            Sentiment.Neutral -> context.getString(R.string.neutral, "\ud83d\ude10")
            Sentiment.Sad -> context.getString(R.string.sad, "\ud83d\ude14")
            else -> ""
        }
    }

    fun getSentimentColor(context: Context): Int {
        return when (sentiment) {
            Sentiment.Happy -> ContextCompat.getColor(context, R.color.happy)
            Sentiment.Neutral -> ContextCompat.getColor(context, R.color.neutral)
            Sentiment.Sad -> ContextCompat.getColor(context, R.color.sad)
            else -> 0
        }
    }
}