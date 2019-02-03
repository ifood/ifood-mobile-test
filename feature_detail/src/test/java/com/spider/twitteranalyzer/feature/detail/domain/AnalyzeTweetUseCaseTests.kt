package com.spider.twitteranalyzer.feature.detail.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import com.spider.twitteranalyzer.base.test.ExecutorThreadTestScope
import com.spider.twitteranalyzer.base.test.PostExecutionThreadTestScope
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import com.spider.twitteranalyzer.feature.detail.repository.TweetAnalyzeRepository
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
class AnalyzeTweetUseCaseTests {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TweetAnalyzeRepository
    @Mock
    private lateinit var mapper: Mapper
    @Mock
    private lateinit var dto: SentimentResponse
    @Mock
    private lateinit var throwable: Throwable
    @Mock
    private lateinit var sentiment: Sentiment

    private lateinit var subject: AnalyzeTweetUseCase
    private val tweet = Tweet(
        text = "@AlecLongstreth this is so fun to follow along during your day! \ud83d\ude4c\ud83c\udffc",
        user = "@x",
        createdAt = "02/01/2019 20:02:18"
    )

    @Before
    fun setUp() {
        subject =
            AnalyzeTweetUseCase.Impl(repository, mapper, ExecutorThreadTestScope(), PostExecutionThreadTestScope())
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `execute - success`() {
        givenRepository(Single.just(dto))
        givenMapping(sentiment)
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(AnalyzeTweetUseCase.Result.OnSuccess(sentiment))
    }

    @Test
    fun `execute - error`() {
        givenRepository(Single.error(throwable))
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(AnalyzeTweetUseCase.Result.OnError(throwable))
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenRepository(single: Single<SentimentResponse>) {
        BDDMockito.given(repository.analyzeTweet(tweet)).willReturn(single)
    }

    private fun givenMapping(sentiment: Sentiment) {
        BDDMockito.given(mapper.map(dto)).willReturn(sentiment)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenUseCaseIsExecuted() {
        subject.execute(tweet)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenLiveDataShouldHaveCorrectValue(result: AnalyzeTweetUseCase.Result) {
        Truth.assertThat(subject.getLiveData().value).isEqualTo(result)
    }
}