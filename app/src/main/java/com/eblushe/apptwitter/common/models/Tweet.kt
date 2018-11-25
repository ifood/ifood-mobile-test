package com.eblushe.apptwitter.common.models

class Tweet(
    var id: Long? = null,
    var text: String? = null,
    var created_at: String? = null,
    var user: User? = null
)
