package com.rlino.ifoodtwitterchallenge.data.twitter

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class TwitterModule {

    @Provides
    fun provideTwitterApi(retrofit: Retrofit): TwitterApi {
        return retrofit.create(TwitterApi::class.java)
    }

}