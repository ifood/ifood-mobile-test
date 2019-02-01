package com.andre.test.feature

import com.andre.test.core.network.NetworkResponse
import com.andre.test.features.AnalyzeSentiment
import com.andre.test.features.GetTweets
import com.andre.test.features.MainViewModel
import com.andre.test.features.SentimentState.*
import com.andre.test.features.UiState.*
import com.andre.test.mock.MockedObjects
import com.google.api.services.language.v1.model.Sentiment
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.whenever
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.stubbing.Answer
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var getTweets: GetTweets
    private lateinit var analyzeSentiment: AnalyzeSentiment

    @Before
    fun setUp() {
        getTweets = Mockito.mock(GetTweets::class.java)
        analyzeSentiment = Mockito.mock(AnalyzeSentiment::class.java)

        mainViewModel = MainViewModel(getTweets, analyzeSentiment)
    }

    @Test
    fun `query Tweet should change uiState to loading`() {
        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Loading)
    }

    @Test
    fun `analyze Sentiment should change uiState to loading`() {
        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Loading)
    }

    @Test
    fun `get tweets success response should change uiState to Success`() {
        val networkResponse = Mockito.mock(NetworkResponse.Success::class.java)
        val search = Mockito.mock(Search::class.java)
        val tweet = Mockito.mock(Tweet::class.java)

        whenever(getTweets.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })
        given { networkResponse.response }.willReturn(search)

        val field = Search::class.java.getDeclaredField("tweets")
        field.isAccessible = true
        field.set(search, listOf(tweet))

        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Success)
    }

    @Test
    fun `get tweets empty response should change uiState to Empty`() {
        val networkResponse = Mockito.mock(NetworkResponse.Success::class.java)
        val search = Mockito.mock(Search::class.java)

        whenever(getTweets.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })
        given { networkResponse.response }.willReturn(search)

        val field = Search::class.java.getDeclaredField("tweets")
        field.isAccessible = true
        field.set(search, emptyList<Tweet>())

        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Empty)
    }

    @Test
    fun `get tweets FetchFailure response should change uiState to Error`() {
        val networkResponse = Mockito.mock(NetworkResponse.FetchFailure::class.java)

        whenever(getTweets.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })

        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Error)
    }

    @Test
    fun `get tweets NetworkFailure response should change uiState to Error`() {
        val networkResponse = Mockito.mock(NetworkResponse.FetchFailure::class.java)

        whenever(getTweets.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })

        mainViewModel.queryTweet("twitterUser")

        assert(mainViewModel.uiState.value is Error)
    }

    @Test
    fun `analyze sentiment score of 0,5 should change analyzeData to HappyTweet `() {
        val networkResponse = Mockito.mock(NetworkResponse.Success::class.java)
        val sentiment = Mockito.mock(Sentiment::class.java)

        whenever(analyzeSentiment.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })
        given { networkResponse.response }.willReturn(sentiment)
        given { sentiment.score }.willReturn(0.5f)

        mainViewModel.analyzeSentiment(MockedObjects.validTweetMessage)

        assert(mainViewModel.uiState.value is Success)
        assert(mainViewModel.analyzeData.value is HappyTweet)
    }

    @Test
    fun `analyze sentiment score of -0,8 should change analyzeData to SadTweet `() {
        val networkResponse = Mockito.mock(NetworkResponse.Success::class.java)
        val sentiment = Mockito.mock(Sentiment::class.java)

        whenever(analyzeSentiment.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })
        given { networkResponse.response }.willReturn(sentiment)
        given { sentiment.score }.willReturn(-0.8f)

        mainViewModel.analyzeSentiment(MockedObjects.validTweetMessage)

        assert(mainViewModel.uiState.value is Success)
        assert(mainViewModel.analyzeData.value is SadTweet)
    }

    @Test
    fun `analyze sentiment score of 0 should change analyzeData to NeutralTweet `() {
        val networkResponse = Mockito.mock(NetworkResponse.Success::class.java)
        val sentiment = Mockito.mock(Sentiment::class.java)

        whenever(analyzeSentiment.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })
        given { networkResponse.response }.willReturn(sentiment)
        given { sentiment.score }.willReturn(0f)

        mainViewModel.analyzeSentiment(MockedObjects.validTweetMessage)

        assert(mainViewModel.uiState.value is Success)
        assert(mainViewModel.analyzeData.value is NeutralTweet)
    }

    @Test
    fun `analyze sentiment FetchFailure response should change UiState to Error `() {
        val networkResponse = Mockito.mock(NetworkResponse.FetchFailure::class.java)

        whenever(analyzeSentiment.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })

        mainViewModel.analyzeSentiment(MockedObjects.validTweetMessage)

        assert(mainViewModel.uiState.value is Error)
    }

    @Test
    fun `analyze sentiment NetworkFailure response should change UiState to Error `() {
        val networkResponse = Mockito.mock(NetworkResponse.NetworkFailure::class.java)

        whenever(analyzeSentiment.execute(any(), any())).thenAnswer(Answer {
            val argument = it.getArgument<((networkResponse: NetworkResponse) -> Unit)>(1)
            argument.invoke(networkResponse)
        })

        mainViewModel.analyzeSentiment(MockedObjects.validTweetMessage)

        assert(mainViewModel.uiState.value is Error)
    }
}