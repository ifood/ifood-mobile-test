package com.eblushe.apptwitter.common.apis.twitter.responses

import com.google.gson.annotations.SerializedName

class TweetResponse {
    @SerializedName("id")
    var id: Long? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("user")
    var user: UserResponse? = null

}