package com.spider.twitteranalyzer.feature.detail.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.detail.domain.AnalyzeTweetUseCase
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import javax.inject.Inject

abstract class DetailViewModel : ViewModel() {

    sealed class State {
        data class TweetAnalyzeDone(val sentiment: Sentiment) : State()
        object ShowLoading : State()
        data class ShowError(val throwable: Throwable) : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun analyzeTweet(tweet: Tweet)

    class Impl @Inject constructor(
        private val state: MediatorLiveData<State>,
        private val useCase: AnalyzeTweetUseCase
    ) : DetailViewModel() {

        init {
            state.addSource(useCase.getLiveData(), ::onAnalyzeTweetResult)
        }

        override fun getState(): LiveData<State> = state

        override fun analyzeTweet(tweet: Tweet) {
            state.postValue(State.ShowLoading)
            useCase.execute(tweet)
        }

        private fun onAnalyzeTweetResult(result: AnalyzeTweetUseCase.Result?) {
            when (result) {
                is AnalyzeTweetUseCase.Result.OnSuccess -> state.postValue(State.TweetAnalyzeDone(result.sentiment))
                is AnalyzeTweetUseCase.Result.OnError -> state.value = State.ShowError(result.throwable)
            }
        }

    }
}