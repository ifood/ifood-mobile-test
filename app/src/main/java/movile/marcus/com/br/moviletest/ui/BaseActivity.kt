package movile.marcus.com.br.moviletest.ui

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}