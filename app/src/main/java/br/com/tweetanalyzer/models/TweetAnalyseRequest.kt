package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class TweetAnalyseRequest(@Expose @SerializedName("document") val document: Document,
                               @Expose @SerializedName("encodingType") val encodingType: String = "UTF8")