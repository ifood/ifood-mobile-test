package br.com.ifood.tweetmood.presentation.view.getuser.di;

import br.com.ifood.tweetmood.presentation.view.getuser.GetUserTweetsActivity;
import br.com.ifood.tweetmood.presentation.view.tweets.di.MainTwitterActivityModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by uchoa on 16/06/18.
 */

@Subcomponent( modules = MainTwitterActivityModule.class)
public interface GetUserActivityComponent extends AndroidInjector<GetUserTweetsActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GetUserTweetsActivity>{}
}