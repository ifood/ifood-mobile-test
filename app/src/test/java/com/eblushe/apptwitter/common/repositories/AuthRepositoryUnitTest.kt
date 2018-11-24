package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.apis.twitter.responses.TwitterOAuthToken
import com.eblushe.apptwitter.common.apis.twitter.services.OAuthService
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.tests.BaseUnitTest
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.getKoin
import org.mockito.BDDMockito.given
import org.mockito.Mock

class AuthRepositoryUnitTest : BaseUnitTest() {

    private lateinit var repository: AuthRepository

    @Mock
    lateinit var oAuthService: OAuthService

    @Before
    fun before() {
        repository = getKoin().get()
        repository.oAuthService = oAuthService
    }

    @Test
    fun requestAuthToken() {
        val accessToken = "QWERTY"
        val tokenType = "bearer"
        val grantType = "client_credentials"
        val twitterToken = TwitterOAuthToken()

        twitterToken.accessToken = accessToken
        twitterToken.tokenType = tokenType

        val oauthTokenSingle = Single.just(twitterToken)
        given(oAuthService.requestToken(grantType)).willReturn(oauthTokenSingle)

        repository.requestAuthToken()?.subscribe { oauthToken ->
            assertEquals(accessToken, oauthToken.accessToken)
            assertEquals(tokenType, oauthToken.tokenType)
        }
    }

    @Test
    fun requestAuthToken_WhenCacheIsActive() {
        val accessToken = "QWERTY"
        val tokenType = "bearer"

        given(preferences.getString(OAuthToken.ACCESS_TOKEN_TAG, null)).willReturn(accessToken)

        repository.requestAuthToken()?.subscribe { oauthToken ->
            assertEquals(accessToken, oauthToken.accessToken)
            assertEquals(tokenType, oauthToken.tokenType)
        }
    }
}