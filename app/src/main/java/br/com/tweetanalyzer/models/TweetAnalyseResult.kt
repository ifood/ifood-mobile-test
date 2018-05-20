package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class TweetAnalyseResult(@Expose @SerializedName("documentSentiment") val documentSentiment: DocSentiment)

