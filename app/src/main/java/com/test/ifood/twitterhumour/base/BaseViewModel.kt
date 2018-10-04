package com.test.ifood.twitterhumour.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

open class BaseViewModel<V : BaseView?>(application: Application) : AndroidViewModel(application), Observable {

    protected var registry = PropertyChangeRegistry()

    open var view: V? = null
        protected set

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.remove(callback)
    }

    override fun onCleared() {
        super.onCleared()
        view = null
    }
}