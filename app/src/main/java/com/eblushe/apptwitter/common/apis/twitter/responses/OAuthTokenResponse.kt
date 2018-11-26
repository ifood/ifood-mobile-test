package com.eblushe.apptwitter.common.apis.twitter.responses

import com.google.gson.annotations.SerializedName



class OAuthTokenResponse {

    var authorization : String = ""; get() = "$tokenType $accessToken"

    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("token_type")
    var tokenType: String? = null
}