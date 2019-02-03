package com.spider.twitteranalyzer.feature.detail.viewslice.detail.injection

import android.arch.lifecycle.MutableLiveData
import com.spider.twitteranalyzer.base.injection.scopes.PerActivity
import com.spider.twitteranalyzer.feature.detail.viewslice.detail.DetailViewSlice
import dagger.Module
import dagger.Provides

@Module
class DetailViewSliceModule {

    @Provides
    @PerActivity
    fun provideListViewSlice(actionLiveData: MutableLiveData<DetailViewSlice.Action>): DetailViewSlice =
        DetailViewSlice.Impl(actionLiveData, true)

    @Provides
    @PerActivity
    fun provideListActionLiveData(): MutableLiveData<DetailViewSlice.Action> =
        MutableLiveData()
}