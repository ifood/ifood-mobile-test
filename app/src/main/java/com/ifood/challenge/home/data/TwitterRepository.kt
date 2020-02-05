package com.ifood.challenge.home.data

import com.ifood.challenge.base.common.network.NetworkHandler
import com.ifood.challenge.base.data.BaseRemoteRepository
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import io.reactivex.Single
import javax.inject.Inject

interface TwitterRepository {

    fun searchUsers(userName: String): Single<List<TwitterUser>>

    fun fetchUserTimeline(twitterUser: TwitterUser): Single<List<Tweet>>

    class Remote @Inject constructor(
        private val twitterService: TwitterService,
        private val twitterUserMapper: TwitterUserMapper,
        private val tweetMapper: TweetMapper,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), TwitterRepository {

        override fun searchUsers(userName: String): Single<List<TwitterUser>> =
            request(twitterUserMapper) {
                twitterService.searchUsers(userName)
            }

        override fun fetchUserTimeline(twitterUser: TwitterUser): Single<List<Tweet>> =
            request(tweetMapper) {
                twitterService.fetchUserTimeline(twitterUser.id)
            }
    }
}
