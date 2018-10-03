package com.test.ifood.twitterhumour.welcome.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepository
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import javax.inject.Inject

class WelcomeViewModelFactory @Inject constructor(private val application: Application,
                                                  private val twitterRepository: TwitterRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WelcomeViewModel(application, twitterRepository) as T
    }
}