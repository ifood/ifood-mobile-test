package br.com.ifood.tweetmood.presentation.di;

import br.com.ifood.tweetmood.presentation.view.getuser.GetUserTweetsActivity;
import br.com.ifood.tweetmood.presentation.view.getuser.di.GetUserActivityModule;
import br.com.ifood.tweetmood.presentation.view.getuser.di.GetUserFragmentModule;
import br.com.ifood.tweetmood.presentation.view.tweets.MainTwitterActivity;
import br.com.ifood.tweetmood.presentation.view.tweets.di.MainTwitterActivityModule;
import br.com.ifood.tweetmood.presentation.view.tweets.di.MainTwitterFragmentModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by uchoa on 16/06/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {GetUserActivityModule.class, GetUserFragmentModule.class})
    abstract GetUserTweetsActivity bindGetUserTweetsActivity();

    @ContributesAndroidInjector(modules = { MainTwitterActivityModule.class, MainTwitterFragmentModule.class})
    abstract MainTwitterActivity bindMainTwitterActivity();

}
