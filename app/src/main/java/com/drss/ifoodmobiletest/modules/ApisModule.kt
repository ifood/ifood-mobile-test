package com.drss.ifoodmobiletest.modules

import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import com.drss.ifoodmobiletest.repository.GCloudSentimentApi
import com.drss.ifoodmobiletest.repository.TwitterDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(RetrofitModule::class))
class ApisModule {

    @Singleton
    @Provides
    fun providesGCloudSentimentRepository(gcloudApiInterface: GCloudApiInterface): GCloudSentimentApi {
        return GCloudSentimentApi(gcloudApiInterface)
    }

    @Singleton
    @Provides
    fun providesTwitterApi(): TwitterDataSource {
        return TwitterDataSource.instance
    }

}