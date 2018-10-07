package com.test.ifood.twitterhumour.tweetlist.tweetsoverview.di

import android.arch.lifecycle.ViewModelProviders
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.TweetListActivity
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.viewmodel.TweetListViewModel
import dagger.Module
import dagger.Provides

@Module
class TweetListActivityModule {

    @Provides
    fun providesTweetListViewModel(activity: TweetListActivity): TweetListViewModel {
        return ViewModelProviders.of(activity).get(TweetListViewModel::class.java)
    }
}