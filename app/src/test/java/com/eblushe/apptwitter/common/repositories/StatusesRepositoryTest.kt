package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import com.eblushe.apptwitter.common.apis.twitter.services.StatusesService
import com.eblushe.apptwitter.common.tests.BaseUnitTest
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.getKoin
import org.mockito.BDDMockito.given
import org.mockito.Mock

class StatusesRepositoryTest : BaseUnitTest()  {

    private lateinit var repository: StatusesRepository

    @Mock
    lateinit var  statusesService: StatusesService

    @Before
    fun before() {
        repository = getKoin().get()
        repository.statusesService = statusesService
    }

    @Test
    fun getUserTimeLine_WhenSuccess() {
        val tweetScreenName = "TweetScreenName"
        val tweetId = 1234L
        val tweetText = "This a tweet test."
        val tweetsResponse = listOf(TweetResponse(id = tweetId, text = tweetText))
        val tweetsResponseSingle = Single.just(tweetsResponse)

        given(statusesService.getUserTimeLine(tweetScreenName)).willReturn(tweetsResponseSingle)

        repository.getUserTimeLine(tweetScreenName).subscribe { tweets ->
            assertEquals(tweetId, tweets[0].id)
            assertEquals(tweetText, tweets[0].text)
        }
    }

    @Test
    fun getUserTimeLine_WhenError() {
        val tweetScreenName = "TweetScreenName"
        val errorMessage = "Screen name not found"
        val responseErrorSingle = Single.error<List<TweetResponse>>(Throwable(errorMessage))

        given(statusesService.getUserTimeLine(tweetScreenName)).willReturn(responseErrorSingle)

        repository.getUserTimeLine(tweetScreenName).doOnError { error ->
            assertEquals(errorMessage, error.message)
        }
    }
}