package bloder.com.domain.payloads.auth

import bloder.com.domain.models.auth.TwitterAuth
import com.google.gson.annotations.SerializedName

data class TwitterAuthPayload(@SerializedName("access_token") private val accessToken: String?) {

    fun toModel() : TwitterAuth = TwitterAuth(accessToken ?: "")
}