package com.andre.test.feature

import com.andre.test.BaseUnitTest
import com.andre.test.core.network.NetworkResponse
import com.andre.test.core.platform.NetworkHandler
import com.andre.test.features.GetTweets
import com.andre.test.features.TwitterRepository
import com.nhaarman.mockitokotlin2.given
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.services.SearchService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class TwitterRepositoryTest: BaseUnitTest() {

    private lateinit var twitterRepository: TwitterRepository

    @Mock private lateinit var searchService: SearchService
    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var call : Call<Search>
    @Mock private lateinit var response: Response<Search>

    @Before fun setUp() {
        twitterRepository = TwitterRepository(searchService, networkHandler)
    }

    @Test fun `should return Success if response is successful`() {
        given { networkHandler.isConnected }.willReturn(true)
        given {  searchService.tweets(
            "twitterUser",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ) }.willReturn(call)
        given { call.execute() }.willReturn(response)
        given { response.isSuccessful }.willReturn(true)

        runBlocking {
            val networkResponse = twitterRepository.getTweets(GetTweets.Params("twitterUser"))
            assert(networkResponse is NetworkResponse.Success<*>)
        }
    }

    @Test fun `should return Fetch Failure if response is not succesful`() {
        given { networkHandler.isConnected }.willReturn(true)
        given {  searchService.tweets(
            "twitterUser",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ) }.willReturn(call)
        given { call.execute() }.willReturn(response)
        given { response.isSuccessful }.willReturn(false)

        runBlocking {
            val networkResponse = twitterRepository.getTweets(GetTweets.Params("twitterUser"))
            assert(networkResponse is NetworkResponse.FetchFailure)
        }
    }

    @Test fun `should return Fetch Failure if request throws an Exception`() {
        given { networkHandler.isConnected }.willReturn(true)
        given {  searchService.tweets(
            "twitterUser",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ) }.willReturn(call)
        given { call.execute() }.willThrow(IOException())
        runBlocking {
            val networkResponse = twitterRepository.getTweets(GetTweets.Params("twitterUser"))
            assert(networkResponse is NetworkResponse.FetchFailure)
        }
    }

    @Test fun `should return Network Failure if internet is not connected`() {
        given { networkHandler.isConnected }.willReturn(false)

        runBlocking {
            val networkResponse = twitterRepository.getTweets(GetTweets.Params("twitterUser"))
            assert(networkResponse is NetworkResponse.NetworkFailure)
        }
    }

}