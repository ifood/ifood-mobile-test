package movile.marcus.com.br.moviletest.setup

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import movile.marcus.com.br.moviletest.MovileTestApp


class TestApp : MovileTestApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: TestAppComponent = DaggerTestAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
