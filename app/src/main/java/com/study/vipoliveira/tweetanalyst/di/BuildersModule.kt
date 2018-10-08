package com.study.vipoliveira.tweetanalyst.di
import com.study.vipoliveira.tweetanalyst.ui.tweet.TweetsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [(TwitterModule::class)])
    abstract fun bindTweetsActivity(): TweetsActivity
}
