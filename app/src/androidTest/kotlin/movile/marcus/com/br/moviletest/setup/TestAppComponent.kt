package movile.marcus.com.br.moviletest.setup

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import movile.marcus.com.br.moviletest.di.ActivitiesModule
import movile.marcus.com.br.moviletest.di.ApplicationModule
import movile.marcus.com.br.moviletest.di.RepositoryModule
import movile.marcus.com.br.moviletest.di.ViewModelModule
import okhttp3.HttpUrl
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (ViewModelModule::class),
        (ActivitiesModule::class),
        (RepositoryModule::class),
        (ApplicationModule::class),
        (TestNetworkModule::class)
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: TestApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestAppComponent.Builder

        fun build(): TestAppComponent
    }
}

object TestServerUrl {
    var url: HttpUrl? = null
}