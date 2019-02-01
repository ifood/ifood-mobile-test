package com.andre.test.feature

import com.andre.test.BaseUnitTest
import com.andre.test.core.interactor.UseCase
import com.andre.test.core.network.NetworkResponse
import com.andre.test.features.GetTweets
import com.andre.test.features.TwitterRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetTweetsTest : BaseUnitTest() {

    private lateinit var getTweets: GetTweets

    @Mock private lateinit var twitterRepository: TwitterRepository
    @Mock private lateinit var networkResponse: NetworkResponse.Success<*>

    @Before fun setUp() {
        getTweets = GetTweets(twitterRepository)
    }

    @Test fun `should get data from twitter repository`() {
        given { twitterRepository.getTweets(any()) }.willReturn(networkResponse)

        runBlocking { getTweets.run(GetTweets.Params("twitterUser")) }

        verify(twitterRepository).getTweets(GetTweets.Params("twitterUser"))
        verifyNoMoreInteractions(twitterRepository)
    }
}