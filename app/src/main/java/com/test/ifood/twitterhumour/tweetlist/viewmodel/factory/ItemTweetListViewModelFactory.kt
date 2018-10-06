package com.test.ifood.twitterhumour.tweetlist.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.viewmodel.ItemTweetListViewModel
import javax.inject.Inject

class ItemTweetListViewModelFactory @Inject constructor(private val application: Application,
                                                        private val tweet: Tweet): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemTweetListViewModel(application, tweet) as T
    }
}