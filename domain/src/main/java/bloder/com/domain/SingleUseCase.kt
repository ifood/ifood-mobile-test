package bloder.com.domain

import com.ir.domain.binding.SingleUseCaseBinding
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase : UseCase() {

    protected fun <T> run(single: Single<T>) : SingleUseCaseBinding<T> {
        return SingleUseCaseBinding(this, single)
    }
}