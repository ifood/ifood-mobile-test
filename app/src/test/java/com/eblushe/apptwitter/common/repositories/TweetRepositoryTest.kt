package com.eblushe.apptwitter.common.repositories

import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import com.eblushe.apptwitter.common.apis.twitter.services.StatusesService
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.tests.BaseUnitTest
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.getKoin
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times

class TweetRepositoryTest : BaseUnitTest()  {

    private lateinit var repository: TweetRepository
    private val tweetScreenName = "screen_name"

    @Mock
    lateinit var  statusesService: StatusesService

    @Before
    fun before() {
        repository = getKoin().get()
        repository.statusesService = statusesService
    }

    @Test
    fun getUserTimeLine_WhenSuccess() {
        val tweetId = 1234L
        val tweetText = "This a tweet test."

        val tweetResponse = TweetResponse()
        tweetResponse.id = tweetId
        tweetResponse.text = tweetText

        val tweet = Tweet()
        tweet.id = tweetId
        tweet.text = tweetText

        val tweetsResponseSingle = Single.just(listOf(tweetResponse))
        val tweetsObservable = Observable.just(listOf(tweet))

        given(statusesService.getUserTimeLine(tweetScreenName)).willReturn(tweetsResponseSingle)
        given(tweetDAO.findByName(tweetScreenName)).willReturn(tweetsObservable)

        repository.getUserTimeLine(tweetScreenName).subscribe { tweets ->
            assertEquals(tweetId, tweets[0].id)
            assertEquals(tweetText, tweets[0].text)
        }

        Mockito.verify(statusesService, times(1)).getUserTimeLine(tweetScreenName)
    }

    @Test
    fun getUserTimeLine_WhenError() {
        val errorMessage = "Screen name not found"
        val responseErrorSingle = Single.error<List<TweetResponse>>(Throwable(errorMessage))
        val responseErrorObservable = Observable.error<List<Tweet>>(Throwable(errorMessage))

        given(statusesService.getUserTimeLine(tweetScreenName)).willReturn(responseErrorSingle)
        given(tweetDAO.findByName(tweetScreenName)).willReturn(responseErrorObservable)

        repository.getUserTimeLine(tweetScreenName).doOnError { error ->
            assertEquals(errorMessage, error.message)
        }
    }
}