package com.eblushe.apptwitter.common.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Tweet(
    @PrimaryKey var id: Long? = null,
    var text: String? = null,
    var createdAt: String? = null,
    var userId: Long? = null,
    var userName: String? = null
)
