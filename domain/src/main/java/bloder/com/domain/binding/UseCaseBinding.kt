package bloder.com.domain.binding

import bloder.com.domain.UseCase
import io.reactivex.disposables.Disposable


abstract class UseCaseBinding<T>(private val useCase: UseCase) {

    protected fun addDisposable(disposable: Disposable) {
        useCase.addDisposable(disposable)
    }

    abstract fun bind(): T
}