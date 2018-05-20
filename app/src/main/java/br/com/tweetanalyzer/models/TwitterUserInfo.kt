package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
data class TwitterUserInfo(@Expose @SerializedName("id") val userId: Long,
                           @Expose @SerializedName("name") val userName: String,
                           @Expose @SerializedName("screen_name") val screenName: String,
                           @Expose @SerializedName("location") val location: String,
                           @Expose @SerializedName("friends_count") val followingCount: String,
                           @Expose @SerializedName("followers_count") val followersCount: String,
                           @Expose @SerializedName("profile_background_image_url") val backgroundImageUrl: String,
                           @Expose @SerializedName("profile_banner_url") val bannerUrl: String,
                           @Expose @SerializedName("profile_image_url") val imageUrl: String,
                           @Expose @SerializedName("description") val description: String,
                           @Expose @SerializedName("statuses_count") val statusesCount: String)