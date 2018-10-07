package com.test.ifood.twitterhumour.app.di

import com.test.ifood.twitterhumour.app.App
import com.test.ifood.twitterhumour.datasource.di.DataSourceModule
import com.test.ifood.twitterhumour.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBinding::class, NetworkModule::class, DataSourceModule::class, DialogFragmentBinding::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}