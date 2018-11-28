package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.apis.twitter.responses.OAuthTokenResponse
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.services.BaseService
import io.reactivex.Single

class OAuthService(apiProvider: ApiProvider) : BaseService<OAuthEndpoint>() {
    override val endpoint: OAuthEndpoint = apiProvider.twitterClient.create(OAuthEndpoint::class.java)

    fun requestToken(grantType: String) : Single<OAuthTokenResponse> {
        return endpoint.requestToken(grantType)
    }
}