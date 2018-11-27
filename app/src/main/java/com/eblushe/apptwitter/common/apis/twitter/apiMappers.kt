package com.eblushe.apptwitter.common.apis.twitter

import com.eblushe.apptwitter.common.apis.twitter.responses.OAuthTokenResponse
import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.models.Tweet

fun mapToOAuthToken(OAuthTokenResponse: OAuthTokenResponse) : OAuthToken {
    return OAuthToken(OAuthTokenResponse.accessToken, OAuthTokenResponse.tokenType)
}

fun mapToTweet(tweetResponse: TweetResponse) : Tweet {
    return Tweet(
        tweetResponse.id,
        tweetResponse.idStr,
        tweetResponse.text,
        tweetResponse.createdAt,
        tweetResponse.user?.id,
        tweetResponse.user?.screenName?.toLowerCase()
    )
}