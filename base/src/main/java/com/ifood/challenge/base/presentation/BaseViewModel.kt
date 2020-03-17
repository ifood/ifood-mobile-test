package com.ifood.challenge.base.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifood.challenge.base.common.exception.AppException
import com.ifood.challenge.base.common.exception.UnknownException
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val appException = MutableLiveData<AppException>()

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun handleFailure(error: Throwable) {
        Timber.e(error)
        when (error) {
            is AppException -> appException.postValue(error)
            else -> appException.postValue(UnknownException)
        }
    }
}
