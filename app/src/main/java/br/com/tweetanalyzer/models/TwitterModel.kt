package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
data class TwitterModel(@SerializedName("created_at") val createAt: String,
                        @SerializedName("id") val id: Long,
                        @SerializedName("text") val description: String,
                        @SerializedName("retweet_count") val retweetCount: String,
                        @SerializedName("favorite_count") val tweetLikesCount: String,
                        @SerializedName("user") val userInfo: TwitterUserInfo)