package com.spider.twitteranalyzer.injection

import android.content.Context
import com.spider.twitteranalyzer.App
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule {

    @Binds
    @PerApplication
    abstract fun application(app: App): Context
}