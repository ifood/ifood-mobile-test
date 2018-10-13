package com.study.vipoliveira.tweetanalyst.di

import android.app.Application
import android.content.Context
import com.study.vipoliveira.tweetanalyst.App
import com.study.vipoliveira.tweetanalyst.store.TwitterStorePref
import com.study.vipoliveira.tweetanalyst.store.TwitterStorePrefImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule  {

    @Provides
    fun provideContext(application: App) : Context {
        return application.applicationContext
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }

    @Provides
    fun provideTwitterStorePref(application: App) : TwitterStorePref {
        return TwitterStorePrefImpl(application.applicationContext)
    }
}