package com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.view.ItemTweetListView
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel.ItemTweetListViewModel
import javax.inject.Inject

class ItemTweetListViewModelFactory @Inject constructor(private val application: Application,
                                                        private val view: ItemTweetListView,
                                                        private val tweet: Tweet): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemTweetListViewModel(application, view, tweet) as T
    }
}