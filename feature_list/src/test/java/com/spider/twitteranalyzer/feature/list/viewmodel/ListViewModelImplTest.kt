package com.spider.twitteranalyzer.feature.list.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.list.domain.FetchTweetsUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class ListViewModelImplTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: FetchTweetsUseCase
    @Mock
    private lateinit var data: List<Tweet>
    @Mock
    private lateinit var observer: Observer<ListViewModel.State>

    private lateinit var subject: ListViewModel

    private val state: MediatorLiveData<ListViewModel.State> = MediatorLiveData()
    private val fetchTweetsUseCaseLiveData: MutableLiveData<FetchTweetsUseCase.Result> = MutableLiveData()
    private val userName = "andersoncfsilva"
    private val throwable = RuntimeException("ERROR")

    @Before
    fun setUp() {
        setUpLiveData()
        subject = ListViewModel.Impl(state, useCase)
    }

    private fun setUpLiveData() {
        state.observeForever(observer)
        given(useCase.getLiveData()).willReturn(fetchTweetsUseCaseLiveData)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `fetchTweets - execute`() {
        whenTweetsAreFetched(userName)
        thenObserverShouldReceiveCorrectStates(ListViewModel.State.ShowLoading)
        thenUseCaseShouldBeExecuted(userName)
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    @Test
    fun `fetchTweets - success`() {
        whenFetchedTweetsResult(FetchTweetsUseCase.Result.OnSuccess(data))
        thenObserverShouldReceiveCorrectStates(ListViewModel.State.TweetsLoaded(data))
    }

    @Test
    fun `fetchTweets - success with empty data`() {
        whenFetchedTweetsResult(FetchTweetsUseCase.Result.OnSuccess(emptyList()))
        thenObserverShouldReceiveCorrectStates(ListViewModel.State.TweetsLoadedEmpty)
    }

    @Test
    fun `fetchTweets - error`() {
        whenFetchedTweetsResult(FetchTweetsUseCase.Result.OnError(throwable))
        thenObserverShouldReceiveCorrectStates(ListViewModel.State.ShowError(throwable))
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenTweetsAreFetched(userName: String) {
        subject.fetchTweets(userName)
    }

    private fun whenFetchedTweetsResult(result: FetchTweetsUseCase.Result) {
        fetchTweetsUseCaseLiveData.value = result
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenUseCaseShouldBeExecuted(userName: String) {
        then(useCase).should().execute(userName)
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        then(useCase).should().getLiveData()
        then(useCase).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: ListViewModel.State) {
        expected.forEach { then(observer).should().onChanged(it) }
        then(observer).shouldHaveNoMoreInteractions()
    }
}
