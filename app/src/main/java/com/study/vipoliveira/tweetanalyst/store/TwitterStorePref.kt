package com.study.vipoliveira.tweetanalyst.store

interface TwitterStorePref {
    fun saveToken(token: String)
    fun getToken(): String?
}