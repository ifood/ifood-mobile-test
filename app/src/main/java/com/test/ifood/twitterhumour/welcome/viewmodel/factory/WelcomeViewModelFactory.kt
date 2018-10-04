package com.test.ifood.twitterhumour.welcome.viewmodel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.ifood.twitterhumour.datasource.twitter.TwitterRepository
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import javax.inject.Inject

class WelcomeViewModelFactory @Inject constructor(private val application: Application,
                                                  private val twitterRepository: TwitterRepository,
                                                  private val welcomeView: WelcomeView): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WelcomeViewModel(application, twitterRepository, welcomeView) as T
    }
}