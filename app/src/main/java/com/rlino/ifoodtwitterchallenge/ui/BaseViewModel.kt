package com.rlino.ifoodtwitterchallenge.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(
        val disposable: CompositeDisposable = CompositeDisposable()
): ViewModel() {

    override fun onCleared() {
        if(!disposable.isDisposed)
            disposable.dispose()
        super.onCleared()
    }
}