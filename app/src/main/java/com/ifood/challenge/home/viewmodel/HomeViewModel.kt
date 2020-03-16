package com.ifood.challenge.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ifood.challenge.base.data.ViewState
import com.ifood.challenge.base.extensions.addToComposite
import com.ifood.challenge.base.extensions.empty
import com.ifood.challenge.base.extensions.observeOnBackground
import com.ifood.challenge.base.extensions.performOnBack
import com.ifood.challenge.base.presentation.BaseViewModel
import com.ifood.challenge.home.data.google.GoogleRepository
import com.ifood.challenge.home.data.twitter.TwitterRepository
import com.ifood.challenge.home.model.Sentiment
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val SEARCH_TEXT_DEBOUNCE_SECONDS = 1L
private const val SEARCH_MIN_TEXT_LENGTH = 3

class HomeViewModel @Inject constructor(
    private val twitterRepository: TwitterRepository,
    private val googleRepository: GoogleRepository
) : BaseViewModel() {

    private val searchTextObservable = PublishSubject.create<String>()
    private var searchTextDisposable: Disposable? = null

    var currentTwitterUser: TwitterUser? = null

    val searchText = MutableLiveData<String>()
    val twitterUsersResponseState = MutableLiveData<ViewState>()
    val tweetsResponseState = MutableLiveData<ViewState>()
    val tweetAnalyzingSentiment = MutableLiveData<ViewState>()

    fun trySearchUsersAgain() {
        searchText.value?.let { userName -> searchUsers(userName) }
    }

    fun searchUsers(userName: String) {
        compositeDisposable.clear()

        twitterRepository.searchUsers(userName)
            .observeOnBackground()
            .doOnSubscribe { twitterUsersResponseState.postValue(ViewState.Loading) }
            .subscribe({ twitterUsers ->
                twitterUsersResponseState.postValue(ViewState.Complete(twitterUsers))
            }, { error ->
                twitterUsersResponseState.postValue(ViewState.Failed(error))
            })
            .addToComposite(compositeDisposable)
    }

    fun tryFetchUserTimelineAgain() {
        currentTwitterUser?.let { twitterUser -> fetchUserTimeline(twitterUser) }
    }

    fun fetchUserTimeline(twitterUser: TwitterUser) {
        compositeDisposable.clear()

        currentTwitterUser = twitterUser
        twitterRepository.fetchUserTimeline(twitterUser)
            .observeOnBackground()
            .doOnSubscribe { tweetsResponseState.postValue(ViewState.Loading) }
            .subscribe({ tweets ->
                tweetsResponseState.postValue(ViewState.Complete(tweets))
            }, ::handleFailure)
            .addToComposite(compositeDisposable)
    }

    fun analyzeSentiment(tweet: Tweet) {
        googleRepository.analyzeSentiment(tweet.text)
            .observeOnBackground()
            .doOnSubscribe { notifyTweetChange(tweet, true) }
            .subscribe({ sentiment ->
                notifyTweetChange(tweet, false, sentiment)
            }, { error ->
                notifyTweetChange(tweet, false, error = error)
            }).addToComposite(compositeDisposable)
    }

    private fun notifyTweetChange(
        tweet: Tweet,
        isLoading: Boolean,
        sentiment: Sentiment? = null,
        error: Throwable? = null
    ) {
        tweet.isLoading = isLoading
        sentiment?.let { tweet.sentiment = sentiment }
        val viewState = if (error == null) ViewState.Complete(tweet) else ViewState.Failed(error)
        tweetAnalyzingSentiment.postValue(viewState)
    }

    fun observeSearchTextChange() {
        searchTextDisposable = searchTextObservable.debounce(
                SEARCH_TEXT_DEBOUNCE_SECONDS,
                TimeUnit.SECONDS
            ).filter { it.isNotBlank() }
            .performOnBack()
            .observeOnBackground()
            .subscribe { newText ->
                searchText.postValue(newText)
            }
    }

    fun onTextChange(query: String) {
        when {
            query.length >= SEARCH_MIN_TEXT_LENGTH -> searchTextObservable.onNext(query)
            else -> searchTextObservable.onNext(String.empty())
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchTextDisposable?.let { disposable ->
            if (!disposable.isDisposed) disposable.dispose()
        }
    }
}
