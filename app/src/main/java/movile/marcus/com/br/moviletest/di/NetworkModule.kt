package movile.marcus.com.br.moviletest.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import movile.marcus.com.br.moviletest.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("HTTP_URL")
    fun provideHttpUrl() = BuildConfig.TWITTER_API

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Provides
    @IntoSet
    fun provideInterceptor() = Interceptor { chain ->
        val request = chain.request()

        chain.proceed(request)
    }
}