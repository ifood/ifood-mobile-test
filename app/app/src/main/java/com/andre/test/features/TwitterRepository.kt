package com.andre.test.features

import com.andre.test.core.network.NetworkResponse
import com.andre.test.core.network.Repository
import com.andre.test.core.platform.NetworkHandler
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.services.SearchService
import javax.inject.Inject

class TwitterRepository
@Inject constructor(
    private val searchService: SearchService,
    private val networkHandler: NetworkHandler
) : Repository() {

    fun getTweets(params: GetTweets.Params): NetworkResponse {
        return when (networkHandler.isConnected) {
            true -> request<Search>(
                searchService.tweets(
                    params.twitterUser,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
            false -> NetworkResponse.NetworkFailure()
        }
    }
}