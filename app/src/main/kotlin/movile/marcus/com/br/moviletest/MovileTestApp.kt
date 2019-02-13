package movile.marcus.com.br.moviletest

import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import movile.marcus.com.br.moviletest.di.DaggerAppComponent

open class MovileTestApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

    override fun onCreate() {
        super.onCreate()
        initHawk()
    }

    private fun initHawk() {
        Hawk.init(this).build()
    }
}