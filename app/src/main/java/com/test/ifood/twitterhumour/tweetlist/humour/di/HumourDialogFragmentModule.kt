package com.test.ifood.twitterhumour.tweetlist.humour.di

import android.arch.lifecycle.ViewModelProviders
import com.test.ifood.twitterhumour.tweetlist.humour.HumourDialogFragment
import com.test.ifood.twitterhumour.tweetlist.humour.viewmodel.HumourViewModel
import com.test.ifood.twitterhumour.tweetlist.humour.viewmodel.factory.HumourViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HumourDialogFragmentModule {

    @Provides
    fun providesHumourViewModel(dialogFragment: HumourDialogFragment, factory: HumourViewModelFactory): HumourViewModel {
        return ViewModelProviders.of(dialogFragment, factory).get(HumourViewModel::class.java)
    }
}