package com.spider.twitteranalyzer.feature.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.list.domain.FetchTweetsUseCase

abstract class ListViewModel : ViewModel() {

    sealed class State {
        data class TweetsLoaded(val tweets: List<Tweet>) : State()
        object TweetsLoadedEmpty : State()
        object ShowLoading : State()
        data class ShowError(val throwable: Throwable) : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchTweets(userName: String)


    class Impl(
        private val state: MediatorLiveData<State>,
        private val useCase: FetchTweetsUseCase
    ) : ListViewModel() {

        init {
            state.addSource(useCase.getLiveData(), ::onFetchTweetsResult)
        }

        override fun onCleared() {
            useCase.cleanUp()
        }

        override fun getState(): LiveData<State> = state

        override fun fetchTweets(userName: String) {
            state.postValue(State.ShowLoading)
            useCase.execute(userName)
        }

        private fun onFetchTweetsResult(result: FetchTweetsUseCase.Result?) {
            when (result) {
                is FetchTweetsUseCase.Result.OnSuccess -> if (result.tweets.any()) {
                    state.postValue(State.TweetsLoaded(result.tweets))
                } else {
                    state.postValue(State.TweetsLoadedEmpty)
                }
                is FetchTweetsUseCase.Result.OnError -> state.value = State.ShowError(result.throwable)
            }
        }
    }
}
