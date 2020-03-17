package com.ifood.challenge.core.di

import android.content.Context
import com.ifood.challenge.base.di.BaseComponent
import com.ifood.challenge.base.di.BaseModule
import com.ifood.challenge.core.di.modules.ApplicationModule
import com.ifood.challenge.core.di.modules.NetworkModule
import com.ifood.challenge.core.di.modules.RepositoryModule
import com.ifood.challenge.core.di.modules.ViewModelModule
import com.ifood.challenge.core.di.scopes.ApplicationScope
import dagger.Component
import javax.inject.Singleton

/**
 * Function to get the current [ApplicationComponent] instance
 */
fun app() = ApplicationComponent.INSTANCE

@ApplicationScope
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        BaseModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent : BaseComponent {

    val context: Context

    companion object {
        lateinit var INSTANCE: ApplicationComponent
    }
}
