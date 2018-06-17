package br.com.ifood.tweetmood.presentation.view.tweets.di;

import br.com.ifood.tweetmood.presentation.view.tweets.MainTwitterActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by uchoa on 16/06/18.
 */

@Subcomponent( modules = MainTwitterActivityModule.class)
public interface MainTwitterActivityComponent extends AndroidInjector<MainTwitterActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainTwitterActivity>{}
}
