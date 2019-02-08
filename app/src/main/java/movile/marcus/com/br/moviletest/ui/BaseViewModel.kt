package movile.marcus.com.br.moviletest.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import movile.marcus.com.br.moviletest.model.Api

abstract class BaseViewModel(private val api: Api) : ViewModel() {

    var disposables = CompositeDisposable()

    val loading = MutableLiveData<Boolean>()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}