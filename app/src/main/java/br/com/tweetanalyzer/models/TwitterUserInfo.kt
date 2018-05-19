package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
data class TwitterUserInfo(@SerializedName("id") val userId: Long,
                           @SerializedName("name") val userName: String,
                           @SerializedName("screen_name") val screenName: String,
                           @SerializedName("location") val location: String,
                           @SerializedName("friends_count") val followingCount: String,
                           @SerializedName("followers_count") val followersCount: String,
                           @SerializedName("profile_background_image_url") val backgroundImageUrl: String,
                           @SerializedName("profile_banner_url") val bannerUrl: String,
                           @SerializedName("profile_image_url") val imageUrl: String,
                           @SerializedName("description") val description: String,
                           @SerializedName("statuses_count") val statusesCount: String)