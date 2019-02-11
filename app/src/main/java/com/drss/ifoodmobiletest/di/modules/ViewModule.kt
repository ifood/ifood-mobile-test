package com.drss.ifoodmobiletest.di.modules

import com.drss.ifoodmobiletest.view.MainActivity
import com.drss.ifoodmobiletest.view.SentimentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectSentimentActivity(): SentimentActivity

}