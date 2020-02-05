package com.ifood.challenge.home

import com.ifood.challenge.base.BaseTests
import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.home.data.TweetMapper
import com.ifood.challenge.home.data.TwitterRepository
import com.ifood.challenge.home.data.TwitterService
import com.ifood.challenge.home.data.TwitterUserMapper
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import org.junit.Before
import org.junit.Test
import retrofit2.create
import testCommon.di.retrofit
import testCommon.home.TWITTER_USER
import testCommon.utils.JsonFile
import testCommon.utils.assertCompleted
import testCommon.utils.assertWithError
import testCommon.utils.fromJsonList

class TwitterRepositoryTests : BaseTests() {

    private val service: TwitterService = retrofit.create()

    private val twitterUserMapper = TwitterUserMapper()

    private val tweetMapper = TweetMapper()

    private lateinit var repository: TwitterRepository.Remote

    private val listOfUsers: List<TwitterUser> by lazy { twitterUserMapper.copyValues(JsonFile.Twitter.SEARCH_USER.fromJsonList()) }

    private val listOfTweets: List<Tweet> by lazy { tweetMapper.copyValues(JsonFile.Twitter.USER_TIMELINE.fromJsonList()) }

    override val isMockServerEnabled: Boolean get() = true

    @Before
    override fun setUp() {
        super.setUp()
        repository = TwitterRepository.Remote(
            service,
            twitterUserMapper,
            tweetMapper,
            networkHandler
        )
    }

    @Test
    fun `should return list of users when search for user name`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.searchUsers("user").test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(listOfUsers)
            assertNoErrors()
        }
    }

    @Test
    fun `should return list of tweets when fetch user timeline`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.fetchUserTimeline(TWITTER_USER).test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(listOfTweets)
            assertNoErrors()
        }
    }

    @Test
    fun `should throws network error when search user without internet connection`() {
        stubNetworkHandlerIsConnected(false)

        val testObserver = repository.searchUsers("user").test()

        testObserver.assertWithError(NetworkError)
    }

    @Test
    fun `should throws network error when user without internet connection`() {
        stubNetworkHandlerIsConnected(false)

        val testObserver = repository.fetchUserTimeline(TWITTER_USER).test()

        testObserver.assertWithError(NetworkError)
    }
}
