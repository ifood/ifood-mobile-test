package com.eblushe.apptwitter.common.apis.twitter.responses

import com.google.gson.annotations.SerializedName



class TwitterOAuthToken {

    var authorization : String = ""; get() = "$tokenType $accessToken"

    @SerializedName("access_token")
    val accessToken: String? = null

    @SerializedName("token_type")
    val tokenType: String? = null
}