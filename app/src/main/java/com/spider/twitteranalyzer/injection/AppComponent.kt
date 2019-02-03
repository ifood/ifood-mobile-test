package com.spider.twitteranalyzer.injection

import com.spider.twitteranalyzer.App
import com.spider.twitteranalyzer.base.domain.ThreadsModule
import com.spider.twitteranalyzer.base.frameworks.PluggableFramework
import com.spider.twitteranalyzer.base.injection.BaseModule
import com.spider.twitteranalyzer.base.injection.scopes.PerApplication
import com.spider.twitteranalyzer.base.network.injection.GoogleModule
import com.spider.twitteranalyzer.base.network.injection.NetworkModule
import com.spider.twitteranalyzer.base.network.injection.TwitterModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ThreadsModule::class,
    Bindings::class,
    AppModule::class,
    BaseModule::class,
    NetworkModule::class,
    RouterModule::class,
    TwitterModule::class,
    GoogleModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract override fun build(): AppComponent
    }

    fun frameworks(): List<PluggableFramework>

}
