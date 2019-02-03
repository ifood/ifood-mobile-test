package com.spider.twitteranalyzer.feature.list.viewslice.state.injection

import com.spider.twitteranalyzer.base.injection.scopes.PerActivity
import com.spider.twitteranalyzer.feature.list.viewslice.state.StateViewSlice
import dagger.Module
import dagger.Provides

@Module
class StateViewSliceModule {

    @Provides
    @PerActivity
    fun provideStateViewSlice(viewSlice: StateViewSlice.Impl): StateViewSlice = viewSlice
}
