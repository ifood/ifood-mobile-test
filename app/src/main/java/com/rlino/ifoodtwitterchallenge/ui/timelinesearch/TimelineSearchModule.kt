package com.rlino.ifoodtwitterchallenge.ui.timelinesearch

import android.arch.lifecycle.ViewModel
import com.rlino.ifoodtwitterchallenge.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class TimelineSearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(TimelineSearchViewModel::class)
    abstract fun bindSessionDetailFragmentViewModel(viewModel: TimelineSearchViewModel): ViewModel

}