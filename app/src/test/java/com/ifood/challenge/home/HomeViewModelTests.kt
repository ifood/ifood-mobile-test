package com.ifood.challenge.home

import com.ifood.challenge.base.BaseViewModelTests
import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.base.data.ViewState
import com.ifood.challenge.home.data.google.GoogleRepository
import com.ifood.challenge.home.data.twitter.TwitterRepository
import com.ifood.challenge.home.model.Sentiment
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import com.ifood.challenge.home.viewmodel.HomeViewModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.subjects.SingleSubject
import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import testCommon.home.TWEET
import testCommon.home.TWITTER_USER

class HomeViewModelTests : BaseViewModelTests() {

    @MockK(relaxed = true)
    lateinit var mockTwitterRepository: TwitterRepository

    @MockK(relaxed = true)
    lateinit var mockGoogleRepository: GoogleRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = HomeViewModel(mockTwitterRepository, mockGoogleRepository)
    }

    @Test
    fun `should return list of users when search by user name on repository`() {
        val listOfUsers = listOf(TWITTER_USER)
        val publish = SingleSubject.create<List<TwitterUser>>()
        stubTwitterRepositorySearchUser(publish)

        viewModel.searchUsers("user name")

        assertThat(viewModel.twitterUsersResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onSuccess(listOfUsers)

        assertThat(viewModel.twitterUsersResponseState.value).isEqualTo(
            ViewState.Complete(
                listOfUsers
            )
        )
    }

    @Test
    fun `should throw network error when search by user name on repository without internet`() {
        val publish = SingleSubject.create<List<TwitterUser>>()
        stubTwitterRepositorySearchUser(publish)

        viewModel.searchUsers("user name")

        assertThat(viewModel.twitterUsersResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onError(NetworkError)

        assertThat(viewModel.twitterUsersResponseState.value).isEqualTo(
            ViewState.Failed(
                NetworkError
            )
        )
    }

    @Test
    fun `should return list of tweets when fetch user timeline on repository`() {
        val listOfTweets = listOf(TWEET)
        val publish = SingleSubject.create<List<Tweet>>()
        stubTwitterRepositoryFetchUserTimeline(publish)

        viewModel.fetchUserTimeline(TWITTER_USER)

        assertThat(viewModel.tweetsResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onSuccess(listOfTweets)

        assertThat(viewModel.tweetsResponseState.value).isEqualTo(ViewState.Complete(listOfTweets))
        assertEquals(viewModel.currentTwitterUser, TWITTER_USER)
    }

    @Test
    fun `should throw network error when fetch user timeline on repository without internet`() {
        val publish = SingleSubject.create<List<Tweet>>()
        stubTwitterRepositoryFetchUserTimeline(publish)

        viewModel.fetchUserTimeline(TWITTER_USER)

        assertThat(viewModel.tweetsResponseState.value).isExactlyInstanceOf(ViewState.Loading.javaClass)

        publish.onError(NetworkError)

        assertThat(viewModel.appException.value).isEqualTo(NetworkError)
        assertEquals(viewModel.currentTwitterUser, TWITTER_USER)
    }

    @Test
    fun `should return tweet with sentiment when analyze sentiment on repository`() {
        val tweet = TWEET
        val publish = SingleSubject.create<Sentiment>()
        stubGoogleRepositoryAnalyzeSentiment(publish)

        viewModel.analyzeSentiment(tweet)

        assertThat(viewModel.tweetAnalyzingSentiment.value)
            .isEqualTo(ViewState.Complete(tweet.copy(isLoading = true)))

        publish.onSuccess(Sentiment.Happy)

        assertThat(viewModel.tweetAnalyzingSentiment.value)
            .isEqualTo(ViewState.Complete(tweet.copy(sentiment = Sentiment.Happy)))
    }

    @Test
    fun `should throw network error when analyze sentiment on repository without internet`() {
        val tweet = TWEET
        val publish = SingleSubject.create<Sentiment>()
        stubGoogleRepositoryAnalyzeSentiment(publish)

        viewModel.analyzeSentiment(tweet)

        assertThat(viewModel.tweetAnalyzingSentiment.value)
            .isEqualTo(ViewState.Complete(tweet.copy(isLoading = true)))

        publish.onError(NetworkError)

        assertThat(viewModel.tweetAnalyzingSentiment.value)
            .isEqualTo(ViewState.Failed(NetworkError))
    }

    private fun stubTwitterRepositorySearchUser(singleSubject: SingleSubject<List<TwitterUser>>) {
        every { mockTwitterRepository.searchUsers(any()) } returns singleSubject
    }

    private fun stubTwitterRepositoryFetchUserTimeline(singleSubject: SingleSubject<List<Tweet>>) {
        every { mockTwitterRepository.fetchUserTimeline(any()) } returns singleSubject
    }

    private fun stubGoogleRepositoryAnalyzeSentiment(singleSubject: SingleSubject<Sentiment>) {
        every { mockGoogleRepository.analyzeSentiment(any()) } returns singleSubject
    }
}
