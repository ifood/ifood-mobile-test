package com.eblushe.apptwitter.common.apis.twitter.responses

import com.google.gson.annotations.SerializedName

class TweetResponse() {

    constructor(id: Long?, idStr: String?, text: String?, createdAt: String? = null, user: UserResponse? = null) : this() {
        this.id = id
        this.idStr = idStr
        this.text = text
        this.user = user
        this.createdAt = createdAt
    }

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("id_str")
    var idStr: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("user")
    var user: UserResponse? = null

}