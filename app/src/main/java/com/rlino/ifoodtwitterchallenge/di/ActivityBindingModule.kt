package com.rlino.ifoodtwitterchallenge.di

import com.rlino.ifoodtwitterchallenge.ui.timelinesearch.TimelineSearchActivity
import com.rlino.ifoodtwitterchallenge.ui.timelinesearch.TimelineSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TimelineSearchModule::class])
    internal abstract fun timelineSearchActivity(): TimelineSearchActivity

}