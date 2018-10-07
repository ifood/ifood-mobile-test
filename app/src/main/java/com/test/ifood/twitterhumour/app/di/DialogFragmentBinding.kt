package com.test.ifood.twitterhumour.app.di

import com.test.ifood.twitterhumour.tweetlist.humour.HumourDialogFragment
import com.test.ifood.twitterhumour.tweetlist.humour.di.HumourDialogFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogFragmentBinding {

    @ContributesAndroidInjector(modules = [HumourDialogFragmentModule::class])
    abstract fun bindHumourDialogFragment(): HumourDialogFragment
}