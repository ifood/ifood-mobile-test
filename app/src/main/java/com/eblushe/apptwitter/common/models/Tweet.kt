package com.eblushe.apptwitter.common.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [ Index("idStr", unique = true) ])
class Tweet() {
    @PrimaryKey
    var id: Long? = null
    var idStr: String? = null
    var text: String? = null
    var createdAt: String? = null
    var userId: Long? = null
    var userName: String? = null

    constructor(id: Long? = null,
                    idStr: String? = null,
                    text: String? = null,
                     createdAt: String? = null,
                     userId: Long? = null,
                     userName: String? = null) : this() {
        this.id = id
        this.idStr = idStr
        this.text = text
        this.createdAt = createdAt
        this.userId = userId
        this.userName = userName
    }
}
