package com.spider.twitteranalyzer.base.network.http

import com.google.gson.annotations.SerializedName

data class TokenResponse(

        @SerializedName("access_token")
        val accessToken: String? = null,

        @SerializedName("token_type")
        val tokenType: String? = null
)