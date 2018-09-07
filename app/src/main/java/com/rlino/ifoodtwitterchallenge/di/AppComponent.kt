package com.rlino.ifoodtwitterchallenge.di

import com.rlino.ifoodtwitterchallenge.App
import com.rlino.ifoodtwitterchallenge.data.google.GoogleModule
import com.rlino.ifoodtwitterchallenge.data.twitter.TwitterModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivityBindingModule::class,
            ViewModelModule::class,
            GoogleModule::class,
            TwitterModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}