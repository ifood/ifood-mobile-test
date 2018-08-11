package bloder.com.domain.binding

import bloder.com.domain.UseCase
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

class SingleUseCaseBinding<T>(useCase: UseCase, private val single: Single<T>) : UseCaseBinding<Single<T>>(useCase) {

    private var onSuccess: (T) -> Any? = {}
    private var onError: (Throwable) -> Any? = {}

    fun onSuccess(onSuccess: (T) -> Any?): SingleUseCaseBinding<T> {
        this.onSuccess = onSuccess
        return this
    }

    fun onError(onError: (Throwable) -> Any?): SingleUseCaseBinding<T> {
        this.onError = onError
        return this
    }

    override fun bind(): Single<T> {
        addDisposable(single.subscribeWith(object : DisposableSingleObserver<T>() {
            override fun onSuccess(t: T) {
                try {
                    this@SingleUseCaseBinding.onSuccess(t)
                } catch (ignored: Exception) {
                }

            }

            override fun onError(e: Throwable) {
                try {
                    this@SingleUseCaseBinding.onError(e)
                } catch (ignored: Exception) {
                }

            }
        }))
        return single
    }

    fun subscribe(dsl: SingleUseCaseBinding<T>.() -> Any?) : Single<T> {
        this.dsl()
        return bind()
    }
}