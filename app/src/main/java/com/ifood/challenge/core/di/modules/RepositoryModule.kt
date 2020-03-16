package com.ifood.challenge.core.di.modules

import com.ifood.challenge.core.di.scopes.ApplicationScope
import com.ifood.challenge.home.data.google.GoogleRepository
import com.ifood.challenge.home.data.twitter.TwitterRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideTwitterRepository(dataSource: TwitterRepository.Remote): TwitterRepository =
        dataSource

    @Provides
    @ApplicationScope
    fun provideGoogleRepository(dataSource: GoogleRepository.Remote): GoogleRepository = dataSource

}
