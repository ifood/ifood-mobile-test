package com.test.ifood.twitterhumour.tweetlist.viewmodel

import android.app.Application
import android.databinding.Bindable
import com.test.ifood.twitterhumour.base.BaseView
import com.test.ifood.twitterhumour.base.BaseViewModel
import com.test.ifood.twitterhumour.model.Tweet

class ItemTweetListViewModel(application: Application,
                             private val tweet: Tweet): BaseViewModel<BaseView?>(application) {

    var tweetContent: String = " "
        @Bindable get() {
            return tweet.text
        }
}