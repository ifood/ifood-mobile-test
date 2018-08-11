package bloder.com.domain

import bloder.com.domain.binding.SingleUseCaseBinding
import io.reactivex.Single

abstract class SingleUseCase : UseCase() {

    protected fun <T> run(single: Single<T>) : SingleUseCaseBinding<T> {
        return SingleUseCaseBinding(this, single)
    }
}