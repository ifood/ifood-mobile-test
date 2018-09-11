package com.rlino.ifoodtwitterchallenge.data.twitter

import com.google.gson.annotations.SerializedName

data class TokenResponse(
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("access_token") val accessToken: String
)