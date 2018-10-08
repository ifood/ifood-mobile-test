package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.study.vipoliveira.tweetanalyst.domain.TwitterUseCases
import com.study.vipoliveira.tweetanalyst.ui.viewentity.TweetsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TweetsViewModel(private val twitterUseCases: TwitterUseCases,
                      private val disposable: CompositeDisposable): ViewModel(){
    private val tweetsResponse = MutableLiveData<TweetsResponse>()

    fun tweetsResponse(): MutableLiveData<TweetsResponse> {
        return tweetsResponse
    }

    fun getTweets(userName: String){
        disposable.add(
                twitterUseCases
                        .getTwitter(userName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            _ -> tweetsResponse.value = TweetsResponse.loading()
                        }
                        .subscribe({
                            items -> tweetsResponse.value = TweetsResponse.success(items)
                        },
                                {
                                    t ->  tweetsResponse.value = TweetsResponse.error(t)
                                })
        )
    }
}