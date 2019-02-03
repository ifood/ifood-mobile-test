package com.spider.twitteranalyzer.base.domain

import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ThreadsModule {

    @Provides
    @PerApplication
    fun provideExecutorThread(): ExecutorThread = ExecutorThread.Impl()

    @Provides
    @PerApplication
    fun providePostExecutionThread(): PostExecutionThread = PostExecutionThread.Impl()

}