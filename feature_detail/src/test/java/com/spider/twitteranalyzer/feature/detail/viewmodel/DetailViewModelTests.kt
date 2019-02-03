package com.spider.twitteranalyzer.feature.detail.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.detail.domain.AnalyzeTweetUseCase
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTests {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: AnalyzeTweetUseCase
    @Mock
    private lateinit var data: Sentiment
    @Mock
    private lateinit var observer: Observer<DetailViewModel.State>

    private lateinit var subject: DetailViewModel

    private val state: MediatorLiveData<DetailViewModel.State> = MediatorLiveData()
    private val userCaseLivaData: MutableLiveData<AnalyzeTweetUseCase.Result> = MutableLiveData()
    private val throwable = RuntimeException("ERROR")
    private val tweet = Tweet(
        text = "@AlecLongstreth this is so fun to follow along during your day! \ud83d\ude4c\ud83c\udffc",
        user = "@x",
        createdAt = "02/01/2019 20:02:18"
    )

    @Before
    fun setUp() {
        setUpLiveData()
        subject = DetailViewModel.Impl(state, useCase)
    }

    private fun setUpLiveData() {
        state.observeForever(observer)
        BDDMockito.given(useCase.getLiveData()).willReturn(userCaseLivaData)
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `analyzeTweet - execute`() {
        whenTweetsAreAnalyzed(tweet)
        thenObserverShouldReceiveCorrectStates(DetailViewModel.State.ShowLoading)
        thenUseCaseShouldBeExecuted(tweet)
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    @Test
    fun `analyzeTweet - success`() {
        whenFetchedTweetsResult(AnalyzeTweetUseCase.Result.OnSuccess(data))
        thenObserverShouldReceiveCorrectStates(DetailViewModel.State.TweetAnalyzeDone(data))
    }

    @Test
    fun `analyzeTweet - error`() {
        whenFetchedTweetsResult(AnalyzeTweetUseCase.Result.OnError(throwable))
        thenObserverShouldReceiveCorrectStates(DetailViewModel.State.ShowError(throwable))
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenTweetsAreAnalyzed(tweet: Tweet) {
        subject.analyzeTweet(tweet)
    }

    private fun whenFetchedTweetsResult(result: AnalyzeTweetUseCase.Result) {
        userCaseLivaData.value = result
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenUseCaseShouldBeExecuted(tweet: Tweet) {
        BDDMockito.then(useCase).should().execute(tweet)
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        BDDMockito.then(useCase).should().getLiveData()
        BDDMockito.then(useCase).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: DetailViewModel.State) {
        expected.forEach { BDDMockito.then(observer).should().onChanged(it) }
        BDDMockito.then(observer).shouldHaveNoMoreInteractions()
    }
}