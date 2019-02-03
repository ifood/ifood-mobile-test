package com.spider.twitteranalyzer.feature.detail.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spider.twitteranalyzer.feature.detail.domain.AnalyzeTweetUseCase
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(
    private val useCase: AnalyzeTweetUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return DetailViewModel.Impl(
            MediatorLiveData(),
            useCase
        ) as T
    }
}