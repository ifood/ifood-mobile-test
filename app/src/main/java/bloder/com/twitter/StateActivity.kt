package bloder.com.twitter

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bloder.com.presentation.AppViewModel

abstract class StateActivity<State> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(provideViewModel())
        observeViewModel()
    }

    private fun observeViewModel() = provideViewModel().state().observe(this, Observer {
        it?.let { handleState(it) }
    })

    abstract fun handleState(state: State)
    abstract fun provideViewModel() : AppViewModel<State>
}