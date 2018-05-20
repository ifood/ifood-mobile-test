package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 20/05/18.
 */
data class DocSentiment(@Expose @SerializedName("magnitude") val magnitude: Double,
                        @Expose @SerializedName("score") val score: Double)