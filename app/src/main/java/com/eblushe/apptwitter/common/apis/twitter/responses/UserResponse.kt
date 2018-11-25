package com.eblushe.apptwitter.common.apis.twitter.responses

import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("screen_name")
    var screenName: String? = null

    @SerializedName("description")
    var description: String? = null
}