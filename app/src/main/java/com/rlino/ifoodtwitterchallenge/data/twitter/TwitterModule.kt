package com.rlino.ifoodtwitterchallenge.data.twitter

import dagger.Module
import dagger.Provides

@Module
class TwitterModule {

    @Provides
    fun provideTwitterApi(): TwitterApi {
        return MockTwitterApi()
    }

}