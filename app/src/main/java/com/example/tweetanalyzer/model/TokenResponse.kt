package com.example.tweetanalyzer.model

import com.google.gson.annotations.SerializedName

class TokenResponse(
        @SerializedName("token_type")
        val tokenType: String,
        @SerializedName("access_token")
        val accessToken: String
)