package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
data class TwitterUser(@SerializedName("screen_name") val screenName: String,
                       @SerializedName("name") val name: String,
                       @SerializedName("profile_image_url") val profileImageUrl: String)