package br.com.ifood.tweetmood.presentation.view.getuser.di;

import br.com.ifood.tweetmood.presentation.view.getuser.GetUserTweetsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by uchoa on 16/06/18.
 */

@Module
public abstract class GetUserFragmentModule {

    @ContributesAndroidInjector(modules = GetUserTweetsFragmentModule.class)
    abstract GetUserTweetsFragment providesGetUserTweetsFragmentFactory();
}
