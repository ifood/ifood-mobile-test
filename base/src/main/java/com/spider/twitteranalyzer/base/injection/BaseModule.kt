package com.spider.twitteranalyzer.base.injection

import android.content.Context
import com.spider.twitteranalyzer.base.frameworks.HawkFramework
import com.spider.twitteranalyzer.base.frameworks.PluggableFramework
import com.spider.twitteranalyzer.base.frameworks.TimberFramework
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.view.ErrorMessageFactory
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

    @Provides
    @PerApplication
    fun provideErrorMessageFactory(app: Context): ErrorMessageFactory = ErrorMessageFactory.Impl(app)

    @Provides
    @PerApplication
    internal fun externalFrameworks(): List<PluggableFramework> = arrayListOf(
        TimberFramework(),
        HawkFramework()
    ).toList()
}
