package br.com.fornaro.tweetssentiment.model

import com.google.gson.annotations.SerializedName

data class User(
        val id: Long,
        val name: String,
        @SerializedName("profile_banner_url") val bannerUrl: String?,
        @SerializedName("profile_image_url") val profilePictureUrl: String?
)