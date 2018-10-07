package com.test.ifood.twitterhumour.tweetlist.humour.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.tweetlist.humour.viewmodel.HumourViewModel
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.view.ItemTweetListView
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel.ItemTweetListViewModel
import javax.inject.Inject

class HumourViewModelFactory @Inject constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HumourViewModel(application) as T
    }
}