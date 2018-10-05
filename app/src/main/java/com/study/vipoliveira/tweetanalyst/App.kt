package com.study.vipoliveira.tweetanalyst

import android.app.Activity
import android.app.Application
import com.study.vipoliveira.tweetanalyst.di.AppComponent
import com.study.vipoliveira.tweetanalyst.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    @Inject set

    private val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}
