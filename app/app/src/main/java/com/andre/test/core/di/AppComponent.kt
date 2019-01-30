package com.andre.test.core.di

import android.app.Application
import com.andre.test.core.di.viewmodel.ViewModelModule
import com.andre.test.features.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun inject(mainActivity: MainActivity)

}