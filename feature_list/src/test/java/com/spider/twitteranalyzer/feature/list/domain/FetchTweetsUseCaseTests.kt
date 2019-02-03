package com.spider.twitteranalyzer.feature.list.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import com.spider.twitteranalyzer.base.test.ExecutorThreadTestScope
import com.spider.twitteranalyzer.base.test.PostExecutionThreadTestScope
import com.spider.twitteranalyzer.feature.list.repository.TweetsRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class FetchTweetsUseCaseTests {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private val userName = "andersoncfsilva"

    @Mock
    private lateinit var repository: TweetsRepository
    @Mock
    private lateinit var mapper: Mapper
    @Mock
    private lateinit var dto: List<TweetResponse>
    @Mock
    private lateinit var throwable: Throwable
    @Mock
    private lateinit var tweets: List<Tweet>

    private lateinit var subject: FetchTweetsUseCase

    @Before
    fun setUp() {
        subject =
            FetchTweetsUseCase.Impl(repository, mapper, ExecutorThreadTestScope(), PostExecutionThreadTestScope())
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `execute - success`() {
        givenRepository(Single.just(dto))
        givenMapping(tweets)
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(FetchTweetsUseCase.Result.OnSuccess(tweets))
    }

    @Test
    fun `execute - error`() {
        givenRepository(Single.error(throwable))
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(FetchTweetsUseCase.Result.OnError(throwable))
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenRepository(single: Single<List<TweetResponse>>) {
        BDDMockito.given(repository.fetchTweets(userName)).willReturn(single)
    }

    private fun givenMapping(list: List<Tweet>) {
        BDDMockito.given(mapper.map(dto)).willReturn(list)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenUseCaseIsExecuted() {
        subject.execute(userName)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenLiveDataShouldHaveCorrectValue(result: FetchTweetsUseCase.Result) {
        assertThat(subject.getLiveData().value).isEqualTo(result)
    }

}