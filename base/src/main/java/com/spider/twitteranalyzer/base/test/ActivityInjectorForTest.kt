package com.spider.twitteranalyzer.base.test

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

// :) thanks to https://proandroiddev.com/activity-espresso-test-with-daggers-android-injector-82f3ee564aa4

object ActivityInjectorForTest {
    inline fun <reified T : Activity> create(crossinline block: T.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is T) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(
            Pair<Class<out Activity>, Provider<AndroidInjector.Factory<out Activity>>>(
                T::class.java,
                Provider { factory })
        )
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map, emptyMap())
    }
}