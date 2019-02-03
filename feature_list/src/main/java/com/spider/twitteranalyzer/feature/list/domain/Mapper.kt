package com.spider.twitteranalyzer.feature.list.domain

import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.extension.reformatDate
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun map(tweets: List<TweetResponse>): List<Tweet> {
        return tweets.map {
            Tweet(
                text = it.text,
                createdAt = it.createdAt.reformatDate(),
                user = "@${it.user.name}"
            )
        }
    }
}