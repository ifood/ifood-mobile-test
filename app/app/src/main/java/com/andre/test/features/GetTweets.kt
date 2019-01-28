package com.andre.test.features

import com.andre.test.core.NetworkResponse
import com.andre.test.core.interactor.UseCase

class GetTweets
    constructor(private val twitterRepository: TwitterRepository) : UseCase<NetworkResponse, GetTweets.Params>() {

    override suspend fun run(params: Params) = twitterRepository.getTweets(params)

    data class Params(val twitterUser: String) {

        override fun toString(): String {
            return twitterUser
        }
    }
}