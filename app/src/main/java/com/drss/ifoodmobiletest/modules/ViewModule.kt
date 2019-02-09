package com.drss.ifoodmobiletest.modules

import com.drss.ifoodmobiletest.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule() {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

}