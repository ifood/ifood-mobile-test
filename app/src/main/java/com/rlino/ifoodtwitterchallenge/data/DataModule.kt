package com.rlino.ifoodtwitterchallenge.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideRetrofit(): Retrofit  = RetrofitProvider.retrofit

}