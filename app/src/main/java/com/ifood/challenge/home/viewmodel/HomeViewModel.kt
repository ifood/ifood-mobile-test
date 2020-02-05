package com.ifood.challenge.home.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.ifood.challenge.base.data.ViewState
import com.ifood.challenge.base.extensions.addToComposite
import com.ifood.challenge.base.extensions.observeOnBackground
import com.ifood.challenge.base.extensions.performOnBack
import com.ifood.challenge.base.presentation.BaseViewModel
import com.ifood.challenge.home.data.GoogleRepository
import com.ifood.challenge.home.data.TwitterRepository
import com.ifood.challenge.home.model.Sentiment
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

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

    fun observeSearchTextChange(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                val query = text?.toString() ?: ""
                onTextChange(query)
            }
        })
        searchTextDisposable = searchTextObservable.debounce(1, TimeUnit.SECONDS)
            .filter { it != "" }
            .performOnBack()
            .observeOnBackground()
            .subscribe { newText ->
                searchText.postValue(newText)
            }
    }

    private fun onTextChange(query: String) {
        when {
            query.length >= 3 -> searchTextObservable.onNext(query)
            else -> searchTextObservable.onNext("")
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchTextDisposable?.let { disposable ->
            if (!disposable.isDisposed) disposable.dispose()
        }
    }
}
