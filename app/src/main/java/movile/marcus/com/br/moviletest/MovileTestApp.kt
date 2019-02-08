package movile.marcus.com.br.moviletest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import movile.marcus.com.br.moviletest.di.DaggerAppComponent

class MovileTestApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}