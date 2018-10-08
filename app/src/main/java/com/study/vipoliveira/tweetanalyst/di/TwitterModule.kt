package com.study.vipoliveira.tweetanalyst.di

import com.study.vipoliveira.tweetanalyst.domain.GoogleUseCases
import com.study.vipoliveira.tweetanalyst.domain.TwitterUseCases
import com.study.vipoliveira.tweetanalyst.domain.TwitterUseCasesImp
import com.study.vipoliveira.tweetanalyst.domain.repositories.TwitterRepository
import com.study.vipoliveira.tweetanalyst.domain.repositories.TwitterRepositoryImp
import com.study.vipoliveira.tweetanalyst.domain.service.TwitterService
import com.study.vipoliveira.tweetanalyst.store.TwitterStorePref
import com.study.vipoliveira.tweetanalyst.ui.tweet.TweetsViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class TwitterModule {
    @Provides
    fun provideTweetRepositoryService(twitterService: TwitterService, twitterStorePref: TwitterStorePref): TwitterRepository {
        return TwitterRepositoryImp(twitterService, twitterStorePref)
    }

    @Provides
    fun provideTweetUseCase(tweetsRepository: TwitterRepository): TwitterUseCases {
        return TwitterUseCasesImp(tweetsRepository)
    }

    @Provides
    fun provideTweetViewModelFactory(twitterUseCases: TwitterUseCases,
                                     googleUseCases: GoogleUseCases,
                                     disposable: CompositeDisposable): TweetsViewModelFactory {
        return TweetsViewModelFactory(twitterUseCases,googleUseCases, disposable)
    }
}