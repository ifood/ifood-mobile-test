package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.study.vipoliveira.tweetanalyst.domain.TwitterUseCases
import io.reactivex.disposables.CompositeDisposable

class TweetsViewModelFactory(private val twitterUseCases: TwitterUseCases,
                             private val disposable: CompositeDisposable): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TweetsViewModel::class.java)){
            return TweetsViewModel(twitterUseCases, disposable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
