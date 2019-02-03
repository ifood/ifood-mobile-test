package com.spider.twitteranalyzer.feature.list.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spider.twitteranalyzer.feature.list.domain.FetchTweetsUseCase
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
    private val useCase: FetchTweetsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return ListViewModel.Impl(
            MediatorLiveData(),
            useCase
        ) as T
    }
}
