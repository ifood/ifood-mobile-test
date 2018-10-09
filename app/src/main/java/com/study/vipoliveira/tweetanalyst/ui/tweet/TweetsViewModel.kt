package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.study.vipoliveira.tweetanalyst.domain.GoogleUseCases
import com.study.vipoliveira.tweetanalyst.domain.TwitterUseCases
import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class TweetsViewModel(private val twitterUseCases: TwitterUseCases,
                      private val googleUseCases: GoogleUseCases,
                      private val disposable: CompositeDisposable): ViewModel(){
    private val loading = MutableLiveData<Boolean>()
    private val tweetError = MutableLiveData<Throwable>()
    private val userNotFound = MutableLiveData<Boolean>()
    private val googleError = MutableLiveData<Throwable>()
    private val tweetData = MutableLiveData<MutableList<TweetResponse>>()
    private val googleData = MutableLiveData<TweetResponse>()

    fun isLoading(): MutableLiveData<Boolean>{
        return loading
    }

    fun userNotFound(): MutableLiveData<Boolean>{
        return userNotFound
    }

    fun tweetError(): MutableLiveData<Throwable> {
        return tweetError
    }

    fun googleError(): MutableLiveData<Throwable>{
        return googleError
    }

    fun tweetData(): MutableLiveData<MutableList<TweetResponse>> {
        return tweetData
    }

    fun googleData(): MutableLiveData<TweetResponse> {
        return googleData
    }

    override fun onCleared() {
        disposable.clear()
    }

    fun getTweets(userName: String){
        disposable.add(
                twitterUseCases
                        .getTwitter(userName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            _ -> loading.value = true
                        }
                        .subscribe({
                            items -> tweetData.value = items
                        },
                                {
                                    error ->
                                    if (error is HttpException) {
                                        when (error.code()) {
                                            404 -> {userNotFound.value = true}
                                            else -> tweetError.value = error
                                        }
                                    }
                                })
        )
    }

    fun analyzeTweet(tweet: TweetResponse){
        disposable.add(
                googleUseCases
                        .analyzeTweet(tweet.text)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                           loading.value = true
                        }
                        .subscribe({
                            item ->
                            with (tweet) {
                                val analyzedTweet = TweetResponse(
                                        createdAt,
                                        id,
                                        text,
                                        item
                                )
                                googleData.value = analyzedTweet
                            }

                        },
                                {
                                    t ->  googleError.value = t
                                })
        )
    }
}