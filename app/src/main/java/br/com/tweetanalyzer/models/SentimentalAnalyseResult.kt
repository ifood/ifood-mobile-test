package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class SentimentalAnalyseResult(@SerializedName("magnitude") val magnitude: Int,
                                    @SerializedName("score") val score: Int)