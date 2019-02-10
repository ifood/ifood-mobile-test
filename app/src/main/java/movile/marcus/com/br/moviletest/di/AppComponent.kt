package movile.marcus.com.br.moviletest.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import movile.marcus.com.br.moviletest.MovileTestApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (ViewModelModule::class),
        (ActivitiesModule::class),
        (NetworkModule::class),
        (RepositoryModule::class),
        (ApplicationModule::class)
    ]
)
interface AppComponent : AndroidInjector<MovileTestApp> {

    override fun inject(application: MovileTestApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MovileTestApp): Builder

        fun build(): AppComponent
    }
}