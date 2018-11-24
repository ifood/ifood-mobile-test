package com.eblushe.apptwitter.common.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [ Index("name", unique = true) ])
class User(
    @PrimaryKey
    var id: Long? = null,
    var name: String,
    var screnName: String
)