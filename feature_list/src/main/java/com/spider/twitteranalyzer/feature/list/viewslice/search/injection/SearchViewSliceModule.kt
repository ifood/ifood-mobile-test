package com.spider.twitteranalyzer.feature.list.viewslice.search.injection

import android.arch.lifecycle.MutableLiveData
import com.spider.twitteranalyzer.base.injection.scopes.PerActivity
import com.spider.twitteranalyzer.feature.list.viewslice.search.SearchViewSlice
import dagger.Module
import dagger.Provides

@Module
class SearchViewSliceModule {

    @Provides
    @PerActivity
    fun provideListViewSlice(actionLiveData: MutableLiveData<SearchViewSlice.Action>): SearchViewSlice =
        SearchViewSlice.Impl(actionLiveData, true)

    @Provides
    @PerActivity
    fun provideListActionLiveData(): MutableLiveData<SearchViewSlice.Action> =
        MutableLiveData()
}