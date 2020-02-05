package com.ifood.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TwitterUserRaw(
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val name: String?,
    @Json(name = "screen_name") val screenName: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "profile_image_url_https") val profileImageUrlHttps: String?
)
