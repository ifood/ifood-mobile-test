package com.example.tweetanalyzer.model

import com.google.gson.annotations.SerializedName

class TokenResponse(
        @SerializedName("access_token")
        val accessToken: String
)