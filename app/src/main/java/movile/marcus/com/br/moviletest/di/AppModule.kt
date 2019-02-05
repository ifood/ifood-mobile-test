package movile.marcus.com.br.moviletest.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import movile.marcus.com.br.moviletest.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    open fun provideOkHttpClientBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder
    }

//    @Provides
//    @Singleton
//    fun provideApi(client: OkHttpClient, moshi: Moshi, @Named("HTTP_URL") url: String): Api {
//        return Retrofit.Builder()
//            .client(client)
//            .baseUrl(url)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//            .build()
//            .create(Api::class.java)
//    }
}