package com.drss.ifoodmobiletest

import android.app.Application
import com.drss.ifoodmobiletest.di.modules.RepositoryModule
import com.drss.ifoodmobiletest.di.modules.RetrofitModule
import com.drss.ifoodmobiletest.di.modules.ViewModelModule
import com.drss.ifoodmobiletest.di.modules.ViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
        ViewModule::class
    )
)
public interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun inject(app: Application): Builder

        fun build(): AppComponent
    }
}