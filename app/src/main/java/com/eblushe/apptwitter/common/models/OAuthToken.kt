package com.eblushe.apptwitter.common.models

class OAuthToken(val accessToken: String?, val tokenType: String? = "bearer") {
    var authorization : String = ""; get() = "$tokenType $accessToken"

    fun isValid() = accessToken != null
    fun isNotValid() = accessToken == null


    companion object {
        val ACCESS_TOKEN_TAG: String = "ACCESS_TOKEN_TAG"
    }
}