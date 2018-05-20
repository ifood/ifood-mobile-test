package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class Document(@SerializedName("content") val text: String,
                    @SerializedName("type") val textType: String = "PLAIN-TEXT")