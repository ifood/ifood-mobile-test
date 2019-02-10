package movile.marcus.com.br.moviletest.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import movile.marcus.com.br.moviletest.model.remote.GoogleApi
import movile.marcus.com.br.moviletest.model.remote.TwitterApi
import movile.marcus.com.br.moviletest.model.repository.GoogleRepository
import movile.marcus.com.br.moviletest.model.repository.TwitterRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTwitterRepository(twitterApi: TwitterApi) = TwitterRepository(twitterApi)

    @Singleton
    @Provides
    fun provideGoogleRepository(googleApi: GoogleApi, moshi: Moshi) = GoogleRepository(googleApi, moshi)
}