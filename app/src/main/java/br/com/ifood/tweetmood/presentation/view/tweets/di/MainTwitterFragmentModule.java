package br.com.ifood.tweetmood.presentation.view.tweets.di;

import br.com.ifood.tweetmood.presentation.view.tweets.SentimentalAnalysisFragment;
import br.com.ifood.tweetmood.presentation.view.tweets.ShowTweetsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by uchoa on 16/06/18.
 */

@Module
public abstract class MainTwitterFragmentModule {

    @ContributesAndroidInjector(modules = ShowTweetsFragmentModule.class)
    abstract ShowTweetsFragment providesShowTweetsFragmentFactory();

    @ContributesAndroidInjector(modules = SentimentalAnalysisFragmentModule.class)
    abstract SentimentalAnalysisFragment providesSentimentalAnalysisFragmentFactory();
}
