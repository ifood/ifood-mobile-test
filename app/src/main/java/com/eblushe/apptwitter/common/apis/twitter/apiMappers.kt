package com.eblushe.apptwitter.common.apis.twitter

import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import com.eblushe.apptwitter.common.apis.twitter.responses.OAuthTokenResponse
import com.eblushe.apptwitter.common.apis.twitter.responses.UserResponse
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.models.User

fun mapToOAuthToken(OAuthTokenResponse: OAuthTokenResponse) : OAuthToken {
    return OAuthToken(OAuthTokenResponse.accessToken, OAuthTokenResponse.tokenType)
}

fun mapToTweet(tweetResponse: TweetResponse) : Tweet {
    return Tweet(
        tweetResponse.id,
        tweetResponse.text,
        tweetResponse.createdAt,
        tweetResponse.user?.let { mapToUser(it) }
    )
}

fun mapToUser(userResponse: UserResponse) : User? {
    if (userResponse.name.isNullOrBlank()) {
        return null
    }

    if (userResponse.screenName.isNullOrBlank()) {
        return null
    }

    return User(
        userResponse.id,
        userResponse.name!!,
        userResponse.screenName!!,
        userResponse.description
    )
}