package com.spider.twitteranalyzer.tests.feature.detail

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.spider.twitteranalyzer.base.domain.ExecutorThread
import com.spider.twitteranalyzer.base.domain.PostExecutionThread
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import com.spider.twitteranalyzer.base.view.ErrorMessageFactory
import com.spider.twitteranalyzer.feature.detail.domain.AnalyzeTweetUseCase
import com.spider.twitteranalyzer.feature.detail.domain.Mapper
import com.spider.twitteranalyzer.feature.detail.repository.TweetAnalyzeRepository
import com.spider.twitteranalyzer.feature.detail.viewmodel.DetailViewModel
import com.spider.twitteranalyzer.feature.detail.viewslice.detail.DetailViewSlice
import io.reactivex.Single

object Mocks {

    private val repository: TweetAnalyzeRepository = object : TweetAnalyzeRepository {
        override fun analyzeTweet(tweet: Tweet): Single<SentimentResponse> = Single.just(sentimentResponse)
    }
    private val mapper: Mapper = Mapper()
    private val executorThread = ExecutorThread.Impl()
    private val postExecutionThread = PostExecutionThread.Impl()
    private val sentimentResponse = SentimentResponse(
        documentSentiment = SentimentResponse.DocumentSentiment(
            magnitude = 1.0,
            score = 1.0
        )
    )
    val tweet = Tweet(
        text = "@AlecLongstreth this is so fun to follow along during your day! \ud83d\ude4c\ud83c\udffc",
        user = "@x",
        createdAt = "02/01/2019 20:02:18"
    )
    val mutableLiveData = MutableLiveData<DetailViewSlice.Action>()
    val state: MediatorLiveData<DetailViewModel.State> = MediatorLiveData()
    val useCase: AnalyzeTweetUseCase = AnalyzeTweetUseCase.Impl(repository, mapper, executorThread, postExecutionThread)
    val errorMessageFactory = object : ErrorMessageFactory {
        override fun create(exception: Throwable): ErrorMessageFactory.ErrorMessage {
            return ErrorMessageFactory.ErrorMessage(exception.message!!)
        }
    }
}