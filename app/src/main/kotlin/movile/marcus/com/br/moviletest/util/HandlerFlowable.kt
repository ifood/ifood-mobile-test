package movile.marcus.com.br.moviletest.util

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Flowable
import movile.marcus.com.br.moviletest.model.Resource
import movile.marcus.com.br.moviletest.model.Status
import movile.marcus.com.br.moviletest.ui.BaseViewModel
import retrofit2.HttpException
import java.io.IOException

fun <T> Flowable<T>.toHandlerFlowable() = HandlerFlowable(this)

data class HandlerFlowable<T>(val flowable: Flowable<T>) {

    fun subscribeLiveData(viewModel: BaseViewModel, liveData: ResourceLiveData<T>) {
        viewModel.disposables.add(
            flowable
                .doOnError { viewModel.loading.postValue(false) }
                .subscribe(
                    { liveData.postValue(Resource.success(it)) },
                    { handleError(liveData, it) })
        )
    }

    private fun handleError(liveData: MutableLiveData<Resource<T>>, it: Throwable) {
        (it as HttpException).run {
            when {
                it is IOException -> liveData.postValue(Resource.error(it, Status.INTERNET_ERROR))
                it.response().code() == 404 -> {
                    liveData.postValue(Resource.error(it, Status.NOT_FOUND))
                }
                else -> liveData.postValue(Resource.error(it))
            }
        }
    }
}