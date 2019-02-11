package com.drss.ifoodmobiletest.di.modules

import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun retrofitGCloudApiInstance(): Retrofit {
        return GCloudApiInterface.create()
    }

    @Singleton
    @Provides
    fun gcloudApi(retrofit: Retrofit): GCloudApiInterface {
        return retrofit.create(GCloudApiInterface::class.java)
    }

}