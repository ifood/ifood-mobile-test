package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
data class TwitterModel(@Expose @SerializedName("created_at") val createAt: String,
                        @Expose @SerializedName("id") val id: Long,
                        @Expose @SerializedName("text") var description: String,
                        @Expose @SerializedName("retweet_count") val retweetCount: String,
                        @Expose @SerializedName("favorite_count") val tweetLikesCount: String,
                        @Expose @SerializedName("user") val userInfo: TwitterUserInfo,
                        var score: Double = -2.0)