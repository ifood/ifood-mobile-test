package movile.marcus.com.br.moviletest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import movile.marcus.com.br.moviletest.ui.home.HomeActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun injectorHomeActivity(): HomeActivity
}