package com.spider.twitteranalyzer.injection

import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.view.ScreenRouter
import com.spider.twitteranalyzer.base.view.ScreenRouterImpl
import dagger.Module
import dagger.Provides

@Module
class RouterModule {

    @Provides
    @PerApplication
    fun provideScreenRouter(): ScreenRouter = ScreenRouterImpl()
}