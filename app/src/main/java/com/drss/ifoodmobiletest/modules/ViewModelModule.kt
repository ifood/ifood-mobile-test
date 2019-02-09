package com.drss.ifoodmobiletest.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drss.ifoodmobiletest.di.ViewModelKey
import com.drss.ifoodmobiletest.viewmodel.InjectableViewModelFactory
import com.drss.ifoodmobiletest.viewmodel.UserTweetsViewModel
import dagger.Binds
import dagger.multibindings.IntoMap

abstract class ViewModelModule{

    @Binds
    abstract fun bindViewModelFactory(injectableViewModelFactory: InjectableViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserTweetsViewModel::class)
    abstract fun bindsUserTweetsViewModel(userTweetsViewModel: UserTweetsViewModel): ViewModel


}