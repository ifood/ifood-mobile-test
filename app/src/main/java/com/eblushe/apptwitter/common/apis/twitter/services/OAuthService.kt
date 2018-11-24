package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.services.BaseService

class OAuthService(val apiProvider: ApiProvider) : BaseService<OAuthEndpoint>() {
    override val endpointType: Class<OAuthEndpoint> = OAuthEndpoint::class.java

    override fun get(): OAuthEndpoint {
        return apiProvider.twitterClient.create(endpointType)
    }
}