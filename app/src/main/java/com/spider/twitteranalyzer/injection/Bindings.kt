package com.spider.twitteranalyzer.injection

import com.spider.twitteranalyzer.base.injection.scopes.PerActivity
import com.spider.twitteranalyzer.feature.detail.injection.TweetsDetailModule
import com.spider.twitteranalyzer.feature.detail.view.TweetDetailActivity
import com.spider.twitteranalyzer.feature.list.injection.TweetsListModule
import com.spider.twitteranalyzer.feature.list.view.TweetsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [TweetsListModule::class])
    abstract fun bindTweetsListActivity(): TweetsListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [TweetsDetailModule::class])
    abstract fun bindTweetDetailActivity(): TweetDetailActivity


}
