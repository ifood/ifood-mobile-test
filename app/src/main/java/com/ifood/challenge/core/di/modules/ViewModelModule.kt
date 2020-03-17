package com.ifood.challenge.core.di.modules

import androidx.lifecycle.ViewModel
import com.ifood.challenge.base.di.ViewModelKey
import com.ifood.challenge.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

}
