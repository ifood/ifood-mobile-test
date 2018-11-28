package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.services.BaseService
import io.reactivex.Single

class StatusesService(apiProvider: ApiProvider) : BaseService<StatusesEndpoint>() {
    override val endpoint: StatusesEndpoint = apiProvider.twitterClient.create(StatusesEndpoint::class.java)

    fun getUserTimeLine(screenName: String): Single<List<TweetResponse>> {
        return endpoint.getUserTimeLine(screenName)
    }
}