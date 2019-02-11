package com.drss.ifoodmobiletest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drss.ifoodmobiletest.di.annotations.ViewModelKey
import com.drss.ifoodmobiletest.viewmodel.SentimentViewModel
import com.drss.ifoodmobiletest.viewmodel.UserTweetsViewModel
import com.drss.ifoodmobiletest.viewmodel.factory.InjectableViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(UserTweetsViewModel::class)
    internal abstract fun bindsUserTweetsViewModel(userTweetsViewModel: UserTweetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SentimentViewModel::class)
    internal abstract fun bindsSentimentViewModel(sentimentViewModel: SentimentViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: InjectableViewModelFactory): ViewModelProvider.Factory
}