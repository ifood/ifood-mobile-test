package com.study.vipoliveira.tweetanalyst.di

import com.study.vipoliveira.tweetanalyst.domain.GoogleUseCases
import com.study.vipoliveira.tweetanalyst.domain.GoogleUseCasesImp
import com.study.vipoliveira.tweetanalyst.domain.repositories.GoogleRepository
import com.study.vipoliveira.tweetanalyst.domain.repositories.GoogleRepositoryImp
import com.study.vipoliveira.tweetanalyst.domain.service.GoogleService
import dagger.Module
import dagger.Provides

@Module
class GoogleModule {
    @Provides
    fun provideGoogleRepository(googleService: GoogleService): GoogleRepository {
        return GoogleRepositoryImp(googleService)
    }

    @Provides
    fun provideGoogleUseCases(googleRepository: GoogleRepository): GoogleUseCases {
        return GoogleUseCasesImp(googleRepository)
    }
}