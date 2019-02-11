package com.drss.ifoodmobiletest.di.modules

import com.drss.ifoodmobiletest.repository.GCloudSentimentApi
import com.drss.ifoodmobiletest.repository.TwitterDataSource
import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesTwitterRepository(): TwitterDataSource {
        return TwitterDataSource()
    }

    @Singleton
    @Provides
    fun providesGCloudSentimentApi(gCloudApiInterface: GCloudApiInterface): GCloudSentimentApi {
        return GCloudSentimentApi(gCloudApiInterface)
    }

}