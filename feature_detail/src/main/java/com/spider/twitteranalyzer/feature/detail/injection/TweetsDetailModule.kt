package com.spider.twitteranalyzer.feature.detail.injection

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.spider.twitteranalyzer.base.injection.qualifiers.ForActivity
import com.spider.twitteranalyzer.base.injection.scopes.PerActivity
import com.spider.twitteranalyzer.feature.detail.domain.AnalyzeTweetUseCase
import com.spider.twitteranalyzer.feature.detail.repository.TweetAnalyzeRepository
import com.spider.twitteranalyzer.feature.detail.view.TweetDetailActivity
import com.spider.twitteranalyzer.feature.detail.viewmodel.DetailViewModel
import com.spider.twitteranalyzer.feature.detail.viewmodel.DetailViewModelFactory
import com.spider.twitteranalyzer.feature.detail.viewslice.detail.injection.DetailViewSliceModule
import com.spider.twitteranalyzer.feature.detail.viewslice.state.injection.StateViewSliceModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        TweetsDetailModule.Repository::class,
        TweetsDetailModule.UseCase::class,
        TweetsDetailModule.ViewModel::class,
        TweetsDetailModule.View::class,
        StateViewSliceModule::class,
        DetailViewSliceModule::class
    ]
)
class TweetsDetailModule {

    @Module
    class Repository {
        @Provides
        @PerActivity
        fun provideTweetsRepository(repository: TweetAnalyzeRepository.Impl): TweetAnalyzeRepository = repository
    }

    @Module
    class UseCase {
        @Provides
        @PerActivity
        fun provideFetchTweetsUseCase(useCase: AnalyzeTweetUseCase.Impl): AnalyzeTweetUseCase = useCase
    }


    @Module
    class ViewModel {
        @Provides
        @PerActivity
        fun provideListViewModel(activity: TweetDetailActivity, factory: DetailViewModelFactory): DetailViewModel =
            ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    @Module
    class View {
        @Provides
        @PerActivity
        @ForActivity
        fun provideContext(activity: TweetDetailActivity): Context = activity
    }
}