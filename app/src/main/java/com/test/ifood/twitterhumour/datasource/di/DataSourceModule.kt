package com.test.ifood.twitterhumour.datasource.di

import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepository
import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindsTwitterRepository(twitterRepositoryImpl: TwitterRepositoryImpl): TwitterRepository
}