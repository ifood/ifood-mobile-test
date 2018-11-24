package com.eblushe.apptwitter.common.apis.twitter

import com.eblushe.apptwitter.common.apis.twitter.responses.TwitterOAuthToken
import com.eblushe.apptwitter.common.models.OAuthToken

fun mapToOAuthToken(twitterOAuthToken: TwitterOAuthToken) : OAuthToken {
    return OAuthToken(twitterOAuthToken.accessToken, twitterOAuthToken.tokenType)
}