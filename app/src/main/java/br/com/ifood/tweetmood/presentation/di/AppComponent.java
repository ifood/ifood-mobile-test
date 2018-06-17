package br.com.ifood.tweetmood.presentation.di;

import br.com.ifood.tweetmood.TMApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by uchoa on 16/06/18.
 */

@Component( modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(TMApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(TMApplication application);
        AppComponent build();
    }
}
