package bloder.com.presentation

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class AppViewModel<State> : ViewModel(), LifecycleObserver {

    private val state: MutableLiveData<State> = MutableLiveData()

    fun state() : LiveData<State> = state

    protected fun dispatch(state: State) {
        this.state.postValue(state)
    }
}