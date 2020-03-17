package com.ifood.challenge.base.di

import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class BaseModule {

    @Binds
    internal abstract fun provideBaseViewModelFactory(baseViewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().build()

    }
}
