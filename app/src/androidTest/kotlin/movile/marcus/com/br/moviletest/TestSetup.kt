package movile.marcus.com.br.moviletest

import android.app.Application
import android.content.Context
import android.support.test.espresso.IdlingResource
import android.support.test.runner.AndroidJUnitRunner
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoSet
import movile.marcus.com.br.moviletest.di.*
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}

class TestApp : MovileTestApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}

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
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(app: TestApp): Builder
    }
}

object TestServerUrl {
    var url: HttpUrl? = null
}

@Module
class TestNetworkModule {

    /* Force HTTP scheme for testing */
    @Provides
    @Singleton
    @Named("HTTP_URL")
    fun providesHttpUrl() = "http://localhost/"

    @Provides
    @Singleton
    fun providesOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        val client = builder.build()
        OkHttp3IdlingResource("billing", client.dispatcher())
        return client
    }

    @Provides
    @IntoSet
    fun providesMockWebServerInterceptor(): Interceptor {
        return Interceptor {
            var request = it.request()
            TestServerUrl.url?.let {
                request = request.newBuilder()
                    .url(
                        request.url()
                            .newBuilder()
                            .host(TestServerUrl.url!!.host())
                            .port(TestServerUrl.url!!.port())
                            .build()
                    )
                    .build()
            }
            it.proceed(request)
        }
    }
}

class OkHttp3IdlingResource(val resourceName: String, val dispatcher: Dispatcher) : IdlingResource {

    @Volatile
    internal var callback: IdlingResource.ResourceCallback? = null

    init {
        dispatcher.setIdleCallback {
            val callback = this@OkHttp3IdlingResource.callback
            callback?.onTransitionToIdle()
        }
    }

    override fun getName() = resourceName
    override fun isIdleNow() = dispatcher.runningCallsCount() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }
}