package br.com.ifood.tweetmood.presentation.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.ifood.tweetmood.presentation.view.getuser.di.GetUserActivityComponent;
import br.com.ifood.tweetmood.presentation.view.tweets.di.MainTwitterActivityComponent;
import dagger.Module;
import dagger.Provides;

/**
 * Created by uchoa on 16/06/18.
 */

@Module(subcomponents = {
        GetUserActivityComponent.class,
        MainTwitterActivityComponent.class,
})
public class AppModule {
    @Provides
    @Singleton
    Context providesContext(Application application){
        return application;
    }
}
