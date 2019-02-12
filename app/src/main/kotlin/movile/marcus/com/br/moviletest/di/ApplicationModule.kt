package movile.marcus.com.br.moviletest.di

import android.app.Application
import dagger.Binds
import dagger.Module
import movile.marcus.com.br.moviletest.MovileTestApp

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun application(app: MovileTestApp): Application
}