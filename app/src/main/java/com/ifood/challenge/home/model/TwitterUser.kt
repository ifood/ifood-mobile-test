package com.ifood.challenge.home.model

data class TwitterUser(
    val id: Long,
    val name: String,
    val screenName: String,
    val location: String,
    val profileImageUrlHttps: String
)
