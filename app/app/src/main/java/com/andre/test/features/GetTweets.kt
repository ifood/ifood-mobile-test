package com.andre.test.features

import com.andre.test.core.network.NetworkResponse
import com.andre.test.core.interactor.UseCase
import javax.inject.Inject

class GetTweets
@Inject constructor(private val twitterRepository: TwitterRepository) : UseCase<NetworkResponse, GetTweets.Params>() {

    override suspend fun run(params: Params) = twitterRepository.getTweets(params)

    data class Params(val twitterUser: String) {

        override fun toString(): String {
            return twitterUser
        }
    }
}